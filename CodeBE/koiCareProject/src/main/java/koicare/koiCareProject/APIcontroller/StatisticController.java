package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.StatisticRequest;
import koicare.koiCareProject.dto.response.*;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.repository.KoiStandardRepository;
import koicare.koiCareProject.repository.OrderDetailRepository;
import koicare.koiCareProject.service.RevenueService;
import koicare.koiCareProject.service.StatisticService;
import koicare.koiCareProject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/statistic")
@SecurityRequirement(name = "api")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @Autowired
    KoiFishRepository koiFishRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    RevenueService revenueService;

    @Autowired
    KoiStandardRepository koiStandardRepository;

    @PostMapping("koistandard")
    public ResponseEntity getKoiStandard(@RequestBody StatisticRequest request){
        APIResponse<StatisticResponse> response = new APIResponse<>();

        StatisticResponse statisticResponse = new StatisticResponse();
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID());
        KoiStandard koiStandard = statisticService.getKoiStandardByDate(request);


        //set koiStandard 30 day later
        StatisticRequest request1 = new StatisticRequest();
        Calendar cal = Calendar.getInstance();
        cal.setTime(request.getDate()); // Lấy ngày từ request
        cal.add(Calendar.DATE, 100); // Thêm 100 ngày
        request1.setDate(cal.getTime()); // Cập nhật lại ngày vào request1
        request1.setKoiFishID(request.getKoiFishID());

        KoiStandard koiStandard1 = statisticService.getKoiStandardByDate(request1);

        Date birthday = koiFish.getBirthday();
        // Chuyển đổi từ Date sang LocalDate
        LocalDate koiBirthday = birthday.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Date date = request.getDate();
        // Chuyển đổi từ Date sang LocalDate
        LocalDate requestDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Tính số ngày giữa hai ngày
        long daysBetween = ChronoUnit.DAYS.between(koiBirthday, requestDate);
        long period = (daysBetween / 100) * 100;

        KoiStandard koiStandardAtPeriod = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod
                (koiFish.getKoiVariety(), period);

        double hiWeightMalePlus = (koiStandard1.getHiWeightMale() - koiStandardAtPeriod.getHiWeightMale()) * 0.3333;
        double lowWeightMalePlus = (koiStandard1.getLowWeightMale() - koiStandardAtPeriod.getLowWeightMale()) * 0.3333;
        double hiLengthMalePlus = (koiStandard1.getHiLengthMale() - koiStandardAtPeriod.getHiLengthMale()) * 0.3333;
        double lowLengthMalePlus = (koiStandard1.getLowLengthMale() - koiStandardAtPeriod.getLowLengthMale()) * 0.3333;

        double hiWeightFemalePlus = (koiStandard1.getHiWeightFemale() - koiStandardAtPeriod.getHiWeightFemale()) * 0.3333;
        double lowWeightFemalePlus = (koiStandard1.getLowWeightFemale() - koiStandardAtPeriod.getLowWeightFemale()) * 0.3333;
        double hiLengthFemalePlus = (koiStandard1.getHiLengthFemale() - koiStandardAtPeriod.getHiLengthFemale()) * 0.3333;
        double lowLengthFemalePlus = (koiStandard1.getLowLengthFemale() - koiStandardAtPeriod.getLowLengthFemale()) * 0.3333;

        if(koiFish.getKoiSex().equalsIgnoreCase("Male")){
            statisticResponse.setAgeByDate(daysBetween);
            statisticResponse.setHiWeight(koiStandard.getHiWeightMale());
            statisticResponse.setLowWeight(koiStandard.getLowWeightMale());
            statisticResponse.setHiLength(koiStandard.getHiLengthMale());
            statisticResponse.setLowLength(koiStandard.getLowLengthMale());
            statisticResponse.setHiWeightto(hiWeightMalePlus + koiStandard.getHiWeightMale());
            statisticResponse.setLowWeightto(lowWeightMalePlus + koiStandard.getLowWeightMale());
            statisticResponse.setHiLengthto(hiLengthMalePlus + koiStandard.getHiLengthMale());
            statisticResponse.setLowLengthto(lowLengthMalePlus + koiStandard.getLowLengthMale());
        }else{
            statisticResponse.setAgeByDate(daysBetween);
            statisticResponse.setHiWeight(koiStandard.getHiWeightFemale());
            statisticResponse.setLowWeight(koiStandard.getLowWeightFemale());
            statisticResponse.setHiLength(koiStandard.getHiLengthFemale());
            statisticResponse.setLowLength(koiStandard.getLowLengthFemale());
            statisticResponse.setHiWeightto(hiWeightFemalePlus + koiStandard.getHiWeightFemale());
            statisticResponse.setLowWeightto(lowWeightFemalePlus + koiStandard.getLowWeightFemale());
            statisticResponse.setHiLengthto(hiLengthFemalePlus + koiStandard.getHiLengthFemale());
            statisticResponse.setLowLengthto(lowLengthFemalePlus + koiStandard.getLowLengthFemale());
        }

        response.setResult(statisticResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("revenue")
    public ResponseEntity getRevenue(){
        APIResponse<List<RevenueResponse>> response = new APIResponse<>();
        List<RevenueResponse> revenueResponses = revenueService.getRevenue();
        response.setResult(revenueResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("shoppackage")
    public ResponseEntity getShopPackage(){
        APIResponse<List<PackageNumberResponse>> response = new APIResponse<>();
        List<PackageNumberResponse> packageNumberResponses = revenueService.getShopPackage();
        response.setResult(packageNumberResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("memberpackage")
    public ResponseEntity getMemberPackage(){
        APIResponse<List<PackageNumberResponse>> response = new APIResponse<>();
        List<PackageNumberResponse> packageNumberResponses = revenueService.getMemberPackage();
        response.setResult(packageNumberResponses);
        return ResponseEntity.ok(response);
    }
}
