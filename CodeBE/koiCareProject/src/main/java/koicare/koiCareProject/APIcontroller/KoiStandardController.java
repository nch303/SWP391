package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.KoiStandardFullRequest;
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
    private KoiStandardService koiStandardService;

    @Autowired
    private ModelMapper modelMapper;


    // Lấy KoiStandard theo KoiVarietyID va Period
    @PostMapping("byPeriodandKoiVarietyID")
    public ResponseEntity getKoiStandard(@RequestBody KoiStandardRequest request){
        APIResponse<KoiStandardResponse> response =new APIResponse<>();

        KoiStandard koiStandard = koiStandardService.getKoiStandardByVarietyAndPeriod(request);
        KoiStandardResponse koiStandardResponse = modelMapper.map(koiStandard, KoiStandardResponse.class);
        koiStandardResponse.setKoiVarietyID(koiStandard.getKoiVariety().getKoiVarietyID());
        response.setResult(koiStandardResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("viewall")
    public  ResponseEntity getAllKoiStandard(){
        APIResponse<List<KoiStandardResponse>> response = new APIResponse<>();

        List<KoiStandard> koiStandards = koiStandardService.getAllKoiStandard();
        List<KoiStandardResponse> koiStandardResponses = koiStandards.stream()
                .map(koiStandard -> modelMapper.map(koiStandard,KoiStandardResponse.class))
                .toList();

        response.setResult(koiStandardResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view/{koiStandardID}")
    public ResponseEntity getKoiStandardByID(@PathVariable long koiStandardID){
        APIResponse<KoiStandardResponse> response = new APIResponse<>();

        KoiStandard koiStandard = koiStandardService.getKoiStandardByID(koiStandardID);
        KoiStandardResponse koiStandardResponse = modelMapper.map(koiStandard,KoiStandardResponse.class);
        response.setResult(koiStandardResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{koiStandardID}")
    public ResponseEntity updateKoiStandard(@RequestBody KoiStandardFullRequest request, @PathVariable long koiStandardID){
        APIResponse<KoiStandardResponse> response = new APIResponse<>();

        KoiStandard koiStandard = koiStandardService.updateKoiStandard(request,koiStandardID);
        KoiStandardResponse koiStandardResponse = modelMapper.map(koiStandard,KoiStandardResponse.class);
        response.setResult(koiStandardResponse);
        return ResponseEntity.ok(response);
    }

}
