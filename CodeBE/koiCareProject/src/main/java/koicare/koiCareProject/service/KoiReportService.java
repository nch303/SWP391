package koicare.koiCareProject.service;


import koicare.koiCareProject.dto.request.KoiReportRequest;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.entity.KoiVariety;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class KoiReportService {

    @Autowired
    KoiReportRepository koiReportRepository;

    @Autowired
    KoiStatusRepository koiStatusRepository;

    @Autowired
    KoiFishRepository koiFishRepository;

    @Autowired
    KoiStandardRepository koiStandardRepository;


    @Autowired
    ModelMapper modelMapper;

    //tạo KoiReport
    public KoiReport createKoiReport(KoiReportRequest request) {

        KoiReport koiReport = new KoiReport();

        koiReport.setUpdateDate(request.getUpdateDate());
        koiReport.setLength(request.getLength());
        koiReport.setWeight(request.getWeight());
        koiReport.setKoiFish(koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID()));


        long koiStatusID = createKoiStatus(request);
        koiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(koiStatusID));


        return koiReportRepository.save(koiReport);
    }

    //lấy danh sách KoiReport theo koiFishID
    public List<KoiReport> getKoiReports(long koiFishID) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(koiFishID);
        return koiReportRepository.getKoiReportsByKoiFish(koiFish);
    }

    //lấy KoiReport mới nhất của 1 koiFishID
    public KoiReport getLatestKoiReport(long koiFishID) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(koiFishID);
        List<KoiReport> koiReports = koiReportRepository.getKoiReportsByKoiFish(koiFish);

        long period = 0;
        long leastDate = Long.MAX_VALUE;

        if (koiReports.size() == 0) {
            throw new AppException(ErrorCode.KOIREPORT_NOT_EXISTED);
        } else {
            KoiReport koiReportFinal = new KoiReport();
            for (KoiReport koiReport : koiReports) {
                period = dateBetween(koiReport.getUpdateDate());
                if (period < leastDate){
                    koiReportFinal = koiReport;
                    leastDate = period;
                }
            }
            return koiReportFinal;
        }
    }

    public long dateBetween(Date date) {
        // Chuyển đổi từ Date sang LocalDate
        LocalDate dateFrom = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Date currentDate = new Date();
        LocalDate dateTo = currentDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Tính số ngày giữa hai ngày
        long daysBetween = ChronoUnit.DAYS.between(dateFrom, dateTo);
        return daysBetween;
    }


    //lấy KoiReport theo ID
    public KoiReport getKoiReport(long koiReportID) {
        KoiReport koiReport = koiReportRepository.getKoiReportByKoiReportID(koiReportID);
        if (koiReport != null)
            return koiReport;
        else
            throw new AppException(ErrorCode.KOIREPORT_NOT_EXISTED);
    }

    //update report
    public KoiReport updateKoiReport(long koiReportID, KoiReportRequest request) {
        KoiReport koiReport = koiReportRepository.getKoiReportByKoiReportID(koiReportID);
        if (koiReport != null) {
            koiReport.setUpdateDate(request.getUpdateDate());
            koiReport.setWeight(request.getWeight());
            koiReport.setLength(request.getLength());

            long koiStatusID = createKoiStatus(request);
            koiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(koiStatusID));

            return koiReportRepository.save(koiReport);
        } else
            throw new AppException(ErrorCode.KOIREPORT_NOT_EXISTED);
    }

    //Xóa KoiReport khỏi danh sách
    public void deleteKoiReport(long koiReportID) {
        KoiReport koiReport = koiReportRepository.getKoiReportByKoiReportID(koiReportID);
        if (koiReport != null)
            koiReportRepository.deleteById(koiReportID);
        else
            throw new AppException(ErrorCode.KOIREPORT_NOT_EXISTED);
    }

    //Hàm dùng để tạo koiStatus tự động
    public Long createKoiStatus(KoiReportRequest request) {
        //status phải được tự động add vào DB bằng các so sánh thông tin ngay tại đây
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID());
        KoiVariety koiVariety = koiFish.getKoiVariety();


        Date koiBirthdayDate = koiFish.getBirthday();
        Date updateDateDate = request.getUpdateDate();

        // Chuyển đổi từ Date sang LocalDate
        LocalDate koiBirthday = koiBirthdayDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate updateDate = updateDateDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Tính số ngày giữa hai ngày
        long daysBetween = ChronoUnit.DAYS.between(koiBirthday, updateDate);

        long period = ((daysBetween / 100) * 100);

        System.out.println("Số ngày giữa hai ngày: " + daysBetween);
        System.out.println(koiBirthday);
        System.out.println(updateDateDate);
        System.out.println("period: " + period);


        long koiStatus = 1;
        if (period < 100) {
            koiStatus = 10;
            return koiStatus;
        } else {
            KoiStandard koiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod(koiVariety, period);
            //xét giới tính "male"
            if (koiFish.getKoiSex().contains("male")) {
                //đạt chuẩn
                if (request.getLength() <= koiStandard.getHiLengthMale() && request.getLength() >= koiStandard.getLowLengthMale()
                        && request.getWeight() <= koiStandard.getHiWeightMale() && request.getWeight() >= koiStandard.getLowWeightMale()) {
                    koiStatus = 1;
                }
                //cá thừa cân, ngắn hơn đọo dài chuẩn
                else if (request.getWeight() > koiStandard.getHiWeightMale() && request.getLength() < koiStandard.getLowLengthMale()) {
                    koiStatus = 6;
                }
                //cá thừa cân, dài hơn độ dài chuẩn
                else if (request.getWeight() > koiStandard.getHiWeightMale() && request.getLength() > koiStandard.getHiLengthMale()) {
                    koiStatus = 7;
                }
                //cá thiếu cân, ngắn hơn độ dài chuẩn
                else if (request.getWeight() < koiStandard.getLowWeightMale() && request.getLength() < koiStandard.getLowLengthMale()) {
                    koiStatus = 8;
                }
                //cá thiếu cân, dài hơn độ dài chuẩn
                else if (request.getWeight() < koiStandard.getLowWeightMale() && request.getLength() > koiStandard.getHiLengthMale()) {
                    koiStatus = 9;
                }
                //thừa cân
                else if (request.getWeight() > koiStandard.getHiWeightMale()) {
                    koiStatus = 2;
                }
                //thiếu cân
                else if (request.getWeight() < koiStandard.getLowWeightMale()) {
                    koiStatus = 3;
                }
                //ngắn hơn độ dài chuẩn
                else if (request.getLength() < koiStandard.getLowLengthMale()) {
                    koiStatus = 4;
                }
                //dài hơn độ dài chuẩn
                else {
                    koiStatus = 5;
                }
            } else {
                //đạt chuẩn
                if (request.getLength() <= koiStandard.getHiLengthFemale() && request.getLength() >= koiStandard.getLowLengthFemale()
                        && request.getWeight() <= koiStandard.getHiWeightFemale() && request.getWeight() >= koiStandard.getLowWeightFemale()) {
                    koiStatus = 1;
                }
                //cá thừa cân, ngắn hơn đọo dài chuẩn
                else if (request.getWeight() > koiStandard.getHiWeightFemale() && request.getLength() < koiStandard.getLowLengthFemale()) {
                    koiStatus = 6;
                }
                //cá thừa cân, dài hơn độ dài chuẩn
                else if (request.getWeight() > koiStandard.getHiWeightFemale() && request.getLength() > koiStandard.getHiLengthFemale()) {
                    koiStatus = 7;
                }
                //cá thiếu cân, ngắn hơn độ dài chuẩn
                else if (request.getWeight() < koiStandard.getLowWeightFemale() && request.getLength() < koiStandard.getLowLengthFemale()) {
                    koiStatus = 8;
                }
                //cá thiếu cân, dài hơn độ dài chuẩn
                else if (request.getWeight() < koiStandard.getLowWeightFemale() && request.getLength() > koiStandard.getHiLengthFemale()) {
                    koiStatus = 8;
                }
                //thừa cân
                else if (request.getWeight() > koiStandard.getHiWeightFemale()) {
                    koiStatus = 2;
                }
                //thiếu cân
                else if (request.getWeight() < koiStandard.getLowWeightFemale()) {
                    koiStatus = 3;
                }
                //ngắn hơn độ dài chuẩn
                else if (request.getLength() < koiStandard.getLowLengthFemale()) {
                    koiStatus = 4;
                }
                //dài hơn độ dài chuẩn
                else {
                    koiStatus = 5;
                }
            }
            return koiStatus;
        }
    }
}
