package koicare.koiCareProject.service;

import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.repository.KoiReportRepository;
import koicare.koiCareProject.repository.PondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KoiFoodService {


    @Autowired
    KoiFishRepository koiFishRepository;

    @Autowired
    PondRepository pondRepository;

    @Autowired
    KoiReportService koiReportService;

    //hàm tính lượng thức ăn cho 1 hồ cá
    public double calculateFoodInPond(long pondID, int temperature) {
        List<KoiFish> koiFishes = koiFishRepository.getAllByPond(pondRepository.getPondByPondID(pondID));
        List<KoiReport> koiReports = new ArrayList<>();

        double totalFood = 0;
        for (int i = 0; i < koiFishes.size(); i++) {
            koiReports.add(koiReportService.getLatestKoiReport(koiFishes.get(i).getKoiFishID()));
        }

        for (int i = 0; i < koiReports.size(); i++) {
            double weight = koiReports.get(i).getWeight();

            Date koiBirthdayDate = koiReports.get(i).getKoiFish().getBirthday();

            // Chuyển đổi từ Date sang LocalDate
            LocalDate koiBirthday = koiBirthdayDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            LocalDate currentDate = LocalDate.now(); // Lấy ngày hiện tại

            // Tính số ngày giữa hai ngày
            long daysBetween = ChronoUnit.DAYS.between(koiBirthday, currentDate);
            long period = ((daysBetween / 100) * 100);

            if (period == 0) {
                totalFood += 0.5;
            }

            totalFood += calculateFoodforOneFish(weight, temperature, period);
        }

        return totalFood;
    }

    //hàm tính lượng thức ăn cho 1 con cá
    public double calculateFoodforOneFish(double weight, int temperature, long period) {
        double food = 0;
        //đối với cá dưới 1 tuổi
        if (period < 400) {
            if (temperature < 10) {
                food = 0.2 * 0.05 * weight;
            } else if (temperature <= 20) {
                food = 0.8 * 0.05 * weight;
            } else if (temperature <= 30) {
                food = 1.2 * 0.05 * weight;
            } else throw new AppException(ErrorCode.TEMP_TOO_HOT);
        }
        //đối với cá từ 1 tuổi đến 3 tuổi
        else if (period <= 1100) {
            if (temperature < 10) {
                food = 0.2 * 0.03 * weight;
            } else if (temperature <= 20) {
                food = 0.8 * 0.03 * weight;
            } else if (temperature <= 30) {
                food = 1.2 * 0.03 * weight;
            } else throw new AppException(ErrorCode.TEMP_TOO_HOT);
        }
        //đối với cá trên 3 tuổi
        else {
            if (temperature < 10) {
                food = 0.2 * 0.02 * weight;
            } else if (temperature <= 20) {
                food = 0.8 * 0.02 * weight;
            } else if (temperature <= 30) {
                food = 1.2 * 0.02 * weight;
            } else throw new AppException(ErrorCode.TEMP_TOO_HOT);
        }
        return food;
    }
}
