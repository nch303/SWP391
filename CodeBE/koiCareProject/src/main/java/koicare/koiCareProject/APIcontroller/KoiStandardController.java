package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.KoiStandardRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiReportResponse;
import koicare.koiCareProject.dto.response.KoiStandardResponse;
import koicare.koiCareProject.dto.response.KoiStatusResponse;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.service.KoiStandardService;
import org.modelmapper.AbstractProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/koistandard")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiStandardController {

    @Autowired
    KoiStandardService koiStandardService;

    @Autowired
    ModelMapper modelMapper;


    // Lấy KoiStandard theo KoiVarietyID va Period
    @GetMapping("")
    public ResponseEntity getKoiStandard(@RequestBody KoiStandardRequest request){
        APIResponse<KoiStandardResponse> response =new APIResponse<>();

        KoiStandard koiStandard = koiStandardService.getKoiStandard(request);
        KoiStandardResponse koiStandardResponse = modelMapper.map(koiStandard, KoiStandardResponse.class);
        koiStandardResponse.setKoiVarietyID(koiStandard.getKoiVariety().getKoiVarietyID());
        response.setResult(koiStandardResponse);

        return ResponseEntity.ok(response);
    }

}
