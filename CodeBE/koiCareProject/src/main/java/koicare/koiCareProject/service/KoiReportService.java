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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class KoiReportService {

    @Autowired
    private KoiReportRepository koiReportRepository;

    @Autowired
    private KoiStatusRepository koiStatusRepository;

    @Autowired
    private KoiFishRepository koiFishRepository;

    @Autowired
    private KoiStandardRepository koiStandardRepository;


    //tạo KoiReport
    public KoiReport createKoiReport(KoiReportRequest request) {

        KoiReport newKoiReport = new KoiReport();

        //nếu trùng report sẽ báo lỗi
        Date date = request.getUpdateDate();
        KoiReport koiReport = koiReportRepository.getKoiReportsByUpdateDateAndKoiFish(date, koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID()));
        if (koiReport != null) {
            throw new AppException(ErrorCode.KOIREPORT_EXISTED);
        }

        newKoiReport.setUpdateDate(request.getUpdateDate());
        newKoiReport.setLength(request.getLength());
        newKoiReport.setWeight(request.getWeight());
        newKoiReport.setKoiFish(koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID()));

        long koiStatusID = createKoiStatus(request);
        newKoiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(koiStatusID));


        return koiReportRepository.save(newKoiReport);
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
                if (period < leastDate) {
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
            //nếu trùng report sẽ báo lỗi
//            Date date = request.getUpdateDate();
//            KoiReport oldKoiReport = koiReportRepository.getKoiReportsByUpdateDateAndKoiFish(date, koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID()));
//            if (oldKoiReport != null) {
//                throw new AppException(ErrorCode.KOIREPORT_EXISTED);
//            }

            koiReport.setUpdateDate(koiReport.getUpdateDate());
            koiReport.setWeight(request.getWeight());
            koiReport.setLength(request.getLength());

            long koiStatusID = createKoiStatus(request);
            koiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(koiStatusID));

            return koiReportRepository.save(koiReport);
        } else
            throw new AppException(ErrorCode.KOIREPORT_NOT_EXISTED);
    }

    //Xóa KoiReport khỏi danh sách
    public void deleteKoiReport(long koiReportID) throws ParseException {
        KoiReport koiReport = koiReportRepository.getKoiReportByKoiReportID(koiReportID);
        if (koiReport != null) {
            List<KoiReport> koiReports = koiReportRepository.getKoiReportsByKoiFish(koiReport.getKoiFish());
            if (koiReports.size() == 1) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date today = formatter.parse(formatter.format(new Date()));

                // Sử dụng Calendar để thêm giờ vào Date
                Calendar cal = Calendar.getInstance();
                cal.setTime(today);
                cal.set(Calendar.HOUR_OF_DAY, 7);  // Đặt giờ thành 7
                cal.set(Calendar.MINUTE, 0);       // Đặt phút thành 0
                cal.set(Calendar.SECOND, 0);       // Đặt giây thành 0
                cal.set(Calendar.MILLISECOND, 0);  // Đặt milli giây thành 0

                // Đặt lại giá trị ngày đã thêm giờ
                koiReport.setUpdateDate(cal.getTime());
                koiReport.setLength(0);
                koiReport.setWeight(0);
                koiReport.setKoiFish(koiReport.getKoiFish());
                koiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(11));
                koiReportRepository.save(koiReport);
            } else {
                koiReportRepository.deleteById(koiReportID);
            }
        } else {
            throw new AppException(ErrorCode.KOIREPORT_NOT_EXISTED);
        }
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
            if (koiStandard != null) {
                KoiStandard nextKoiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod
                        (koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID()).getKoiVariety(), period + 100);

                if (daysBetween % 100 <= 31) {
                    koiStandard = koiStandard;
                } else if (daysBetween % 100 <= 61 && daysBetween % 100 > 31) {
                    koiStandard.setHiWeightFemale(koiStandard.getHiWeightFemale()
                            + (nextKoiStandard.getHiWeightFemale() - koiStandard.getHiWeightFemale()) * 0.3333);
                    koiStandard.setMedWeightFemale(koiStandard.getMedWeightFemale()
                            + (nextKoiStandard.getMedWeightFemale() - koiStandard.getMedWeightFemale()) * 0.3333);
                    koiStandard.setLowWeightFemale(koiStandard.getLowWeightFemale()
                            + (nextKoiStandard.getLowWeightFemale() - koiStandard.getLowWeightFemale()) * 0.3333);

                    koiStandard.setHiLengthFemale(koiStandard.getHiLengthFemale()
                            + (nextKoiStandard.getHiLengthFemale() - koiStandard.getHiLengthFemale()) * 0.3333);
                    koiStandard.setMedLengthFemale(koiStandard.getMedLengthFemale()
                            + (nextKoiStandard.getMedLengthFemale() - koiStandard.getMedLengthFemale()) * 0.3333);
                    koiStandard.setLowLengthFemale(koiStandard.getLowLengthFemale()
                            + (nextKoiStandard.getLowLengthFemale() - koiStandard.getLowLengthFemale()) * 0.3333);

                    koiStandard.setHiWeightMale(koiStandard.getHiWeightMale()
                            + (nextKoiStandard.getHiWeightMale() - koiStandard.getHiWeightMale()) * 0.3333);
                    koiStandard.setMedWeightMale(koiStandard.getMedWeightMale()
                            + (nextKoiStandard.getMedWeightMale() - koiStandard.getMedWeightMale()) * 0.3333);
                    koiStandard.setLowWeightMale(koiStandard.getLowWeightMale()
                            + (nextKoiStandard.getLowWeightMale() - koiStandard.getLowWeightMale()) * 0.3333);

                    koiStandard.setHiLengthMale(koiStandard.getHiLengthMale()
                            + (nextKoiStandard.getHiLengthMale() - koiStandard.getHiLengthMale()) * 0.3333);
                    koiStandard.setMedLengthMale(koiStandard.getMedLengthMale()
                            + (nextKoiStandard.getMedLengthMale() - koiStandard.getMedLengthMale()) * 0.3333);
                    koiStandard.setLowLengthMale(koiStandard.getLowLengthMale()
                            + (nextKoiStandard.getLowLengthMale() - koiStandard.getLowLengthMale()) * 0.3333);
                } else {
                    koiStandard.setHiWeightFemale(koiStandard.getHiWeightFemale()
                            + (nextKoiStandard.getHiWeightFemale() - koiStandard.getHiWeightFemale()) * 0.6666);
                    koiStandard.setMedWeightFemale(koiStandard.getMedWeightFemale()
                            + (nextKoiStandard.getMedWeightFemale() - koiStandard.getMedWeightFemale()) * 0.6666);
                    koiStandard.setLowWeightFemale(koiStandard.getLowWeightFemale()
                            + (nextKoiStandard.getLowWeightFemale() - koiStandard.getLowWeightFemale()) * 0.6666);

                    koiStandard.setHiLengthFemale(koiStandard.getHiLengthFemale()
                            + (nextKoiStandard.getHiLengthFemale() - koiStandard.getHiLengthFemale()) * 0.6666);
                    koiStandard.setMedLengthFemale(koiStandard.getMedLengthFemale()
                            + (nextKoiStandard.getMedLengthFemale() - koiStandard.getMedLengthFemale()) * 0.6666);
                    koiStandard.setLowLengthFemale(koiStandard.getLowLengthFemale()
                            + (nextKoiStandard.getLowLengthFemale() - koiStandard.getLowLengthFemale()) * 0.6666);

                    koiStandard.setHiWeightMale(koiStandard.getHiWeightMale()
                            + (nextKoiStandard.getHiWeightMale() - koiStandard.getHiWeightMale()) * 0.6666);
                    koiStandard.setMedWeightMale(koiStandard.getMedWeightMale()
                            + (nextKoiStandard.getMedWeightMale() - koiStandard.getMedWeightMale()) * 0.6666);
                    koiStandard.setLowWeightMale(koiStandard.getLowWeightMale()
                            + (nextKoiStandard.getLowWeightMale() - koiStandard.getLowWeightMale()) * 0.6666);

                    koiStandard.setHiLengthMale(koiStandard.getHiLengthMale()
                            + (nextKoiStandard.getHiLengthMale() - koiStandard.getHiLengthMale()) * 0.6666);
                    koiStandard.setMedLengthMale(koiStandard.getMedLengthMale()
                            + (nextKoiStandard.getMedLengthMale() - koiStandard.getMedLengthMale()) * 0.6666);
                    koiStandard.setLowLengthMale(koiStandard.getLowLengthMale()
                            + (nextKoiStandard.getLowLengthMale() - koiStandard.getLowLengthMale()) * 0.6666);
                }
            }
            //xét giới tính "male"
            if (koiFish.getKoiSex().equalsIgnoreCase("Male")) {
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
