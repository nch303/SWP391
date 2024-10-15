package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.ExpertModeRequest;
import koicare.koiCareProject.dto.response.KoiFoodListResponse;
import koicare.koiCareProject.entity.FeedCoef;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.entity.TempCoef;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.*;
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
    private KoiFishRepository koiFishRepository;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private KoiReportService koiReportService;

    @Autowired
    private FeedCoefRepository feedCoefRepository;

    @Autowired
    private TempCoefRepository tempCoefRepository;

    @Autowired
    private PondService pondService;

    //hàm tính lượng thức ăn cho 1 hồ cá
    public double calculateFoodInPond(long pondID, int temperature, String level) {
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
            long period = daysBetween;

            totalFood += calculateFoodforOneFish(weight, temperature, period, level);
        }
        return totalFood;
    }

    //hàm tính lượng thức ăn cho 1 con cá
    public double calculateFoodforOneFish(double weight, int temperature, long period, String level) {
        double food = 0;

        List<FeedCoef> feedCoefs = feedCoefRepository.findAll();
        for (FeedCoef feedCoef : feedCoefs) {

            //xét xem cá đang ở giai đoạn tuổi nào trong bảng FeedCoef
            if (period >= feedCoef.getAgeFrom() && period <= feedCoef.getAgeTo()) {
                List<TempCoef> tempCoefs = tempCoefRepository.findAll();
                for (TempCoef tempCoef : tempCoefs) {

                    //xét xem cá đang ở nhiệt độ nào trong bảng TempCoef
                    if (temperature >= tempCoef.getTempFrom() && temperature <= tempCoef.getTempTo()) {

                        //xét xem cá đang ở mức độ cho ăn nào trong FeedCoef
                        if (level.contains("Low")) {
                            food = tempCoef.getCoef() * feedCoef.getLow() / 100 * weight;
                        } else if (level.contains("Medium")) {
                            food = tempCoef.getCoef() * feedCoef.getMedium() / 100 * weight;
                        } else {
                            food = tempCoef.getCoef() * feedCoef.getHigh() / 100 * weight;
                        }
                    } else if (temperature > tempCoefs.get(tempCoefs.size() - 1).getTempTo()) {
                        throw new AppException(ErrorCode.TEMP_TOO_HOT);
                    } else if(temperature < tempCoefs.get(0).getTempFrom()){
                        throw new AppException(ErrorCode.TEMP_TOO_COLD);
                    }

                }
            }
        }
        double roundedFood = Math.round(food * 100.0) / 100.0;
        return roundedFood;
    }

    //tính lượng thức ăn cho từng con cá và trả về danh sách
    public List<KoiFoodListResponse> calculateFoodForKoiList(long pondID, int temperature, String level){
        List<KoiFish> koiFishes = koiFishRepository.getAllByPond(pondRepository.getPondByPondID(pondID));
        List<KoiFoodListResponse> koiFoodListResponses = new ArrayList<>();
        for(KoiFish koiFish: koiFishes){
            KoiFoodListResponse koiFoodListResponse = new KoiFoodListResponse();
            koiFoodListResponse.setKoiFishID(koiFish.getKoiFishID());
            koiFoodListResponse.setKoiName(koiFish.getKoiName());
            koiFoodListResponse.setKoiVariety(koiFish.getKoiVariety().getVarietyName());
            koiFoodListResponse.setBirthday(koiFish.getBirthday());
            KoiReport koiReport = koiReportService.getLatestKoiReport(koiFish.getKoiFishID());
            koiFoodListResponse.setLength(koiReport.getLength());
            koiFoodListResponse.setWeight(koiReport.getWeight());
            // Chuyển đổi từ Date sang LocalDate
            LocalDate koiBirthday = koiFoodListResponse.getBirthday().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate currentDate = LocalDate.now(); // Lấy ngày hiện tại
            // Tính số ngày giữa hai ngày
            long daysBetween = ChronoUnit.DAYS.between(koiBirthday, currentDate);
            long period = daysBetween;
            double roundedFood = Math.round(calculateFoodforOneFish(koiReport.getWeight(),temperature, period,level) * 100.0) / 100.0;
            koiFoodListResponse.setFood(roundedFood);
            koiFoodListResponses.add(koiFoodListResponse);
        }
        return koiFoodListResponses;
    }

    //tính khối lượng thức ăn cho cá theo expert mode
    public double expertMode(ExpertModeRequest request){
        double totalWeight = pondService.calculateTotalWeight(request.getPondID());
        double food = totalWeight * request.getPercent() / 100;
        double roundedFood = Math.round(food * 100.0) / 100.0;
        return roundedFood;
    }
}
