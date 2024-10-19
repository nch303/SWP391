package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.StatisticRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.StatisticResponse;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("api/statistic")
@SecurityRequirement(name = "api")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @Autowired
    KoiFishRepository koiFishRepository;

    @PostMapping("koistandard")
    public ResponseEntity getKoiStandard(@RequestBody StatisticRequest request){
        APIResponse<StatisticResponse> response = new APIResponse<>();

        StatisticResponse statisticResponse = new StatisticResponse();
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID());
        KoiStandard koiStandard = statisticService.getKoiStandardByDate(request);

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
        if(koiFish.getKoiSex().equals("male")){
            statisticResponse.setAgeByDate(daysBetween);
            statisticResponse.setHiWeight(koiStandard.getHiWeightMale());
            statisticResponse.setLowWeight(koiStandard.getLowWeightMale());
            statisticResponse.setHiLength(koiStandard.getHiLengthMale());
            statisticResponse.setLowLength(koiStandard.getLowLengthMale());
        }else{
            statisticResponse.setAgeByDate(daysBetween);
            statisticResponse.setHiWeight(koiStandard.getHiWeightFemale());
            statisticResponse.setLowWeight(koiStandard.getLowWeightFemale());
            statisticResponse.setHiLength(koiStandard.getHiLengthFemale());
            statisticResponse.setLowLength(koiStandard.getLowLengthFemale());
        }

        response.setResult(statisticResponse);
        return ResponseEntity.ok(response);
    }
}
