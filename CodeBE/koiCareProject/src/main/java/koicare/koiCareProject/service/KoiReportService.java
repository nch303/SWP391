package koicare.koiCareProject.service;


import koicare.koiCareProject.dto.request.KoiReportRequest;
import koicare.koiCareProject.dto.request.KoiStandardRequest;
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


        long koiStatus = createKoiStatus(request);

        koiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(koiStatus));


        return koiReportRepository.save(koiReport);
    }

    //lấy danh sách KoiReport
    public List<KoiReport> getKoiReports() {
        return koiReportRepository.findAll();
    }

    //lấy KoiReport theo ID
    public KoiReport getKoiReport(long koiReportID) {
        KoiReport koiReport = koiReportRepository.getKoiReportByKoiReportID(koiReportID);
        if (koiReport != null)
            return koiReport;
        else
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
    public Long createKoiStatus(KoiReportRequest request){
        //status phải được tự động add vào DB bằng các so sánh thông tin ngay tại đây
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID());
        KoiVariety koiVariety = koiFish.getKoiVariety();


        Date koiBirthdayDate = koiFish.getBirthday(); // Giả sử đây là ngày sinh của cá koi (kiểu Date)
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

        // Làm tròn số ngày đến bội số gần nhất của 100 và cộng 100
        long period = ((daysBetween / 100) * 100) + 100;

        System.out.println("Số ngày giữa hai ngày: " + daysBetween);
        System.out.println(koiBirthday);
        System.out.println(updateDateDate);
        System.out.println("period: " + period);


        KoiStandard koiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod(koiVariety, period);
        long koiStatus = 1;
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
                koiStatus = 8;
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
