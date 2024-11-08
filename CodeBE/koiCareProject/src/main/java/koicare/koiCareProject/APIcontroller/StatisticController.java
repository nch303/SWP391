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
        if(koiFish.getKoiSex().equalsIgnoreCase("Male")){
            statisticResponse.setAgeByDate(daysBetween);
            statisticResponse.setHiWeight(koiStandard.getHiWeightMale());
            statisticResponse.setLowWeight(koiStandard.getLowWeightMale());
            statisticResponse.setHiLength(koiStandard.getHiLengthMale());
            statisticResponse.setLowLength(koiStandard.getLowLengthMale());
            statisticResponse.setHiWeightto((koiStandard1.getHiWeightMale() - koiStandard.getHiWeightMale()) * 0.3333 + koiStandard.getHiWeightMale());
            statisticResponse.setLowWeightto((koiStandard1.getLowWeightMale() - koiStandard.getLowWeightMale()) * 0.3333 + koiStandard.getLowWeightMale());
            statisticResponse.setHiLengthto((koiStandard1.getHiLengthMale() - koiStandard.getHiLengthMale()) * 0.3333 + koiStandard.getHiLengthMale());
            statisticResponse.setLowLengthto((koiStandard1.getLowLengthMale() - koiStandard.getLowLengthMale()) * 0.3333 + koiStandard.getLowLengthMale());
        }else{
            statisticResponse.setAgeByDate(daysBetween);
            statisticResponse.setHiWeight(koiStandard.getHiWeightFemale());
            statisticResponse.setLowWeight(koiStandard.getLowWeightFemale());
            statisticResponse.setHiLength(koiStandard.getHiLengthFemale());
            statisticResponse.setLowLength(koiStandard.getLowLengthFemale());
            statisticResponse.setHiWeightto((koiStandard1.getHiWeightFemale() - koiStandard.getHiWeightFemale()) * 0.3333 + koiStandard.getHiWeightFemale());
            statisticResponse.setLowWeightto((koiStandard1.getLowWeightFemale() - koiStandard.getLowWeightFemale()) * 0.3333 + koiStandard.getLowWeightFemale());
            statisticResponse.setHiLengthto((koiStandard1.getHiLengthFemale() - koiStandard.getHiLengthFemale()) * 0.3333 + koiStandard.getHiLengthFemale());
            statisticResponse.setLowLengthto((koiStandard1.getLowLengthMale() - koiStandard.getLowLengthMale()) * 0.3333 + koiStandard.getLowLengthFemale());
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
