package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.KoiVarietyRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiFishResponse;
import koicare.koiCareProject.dto.response.KoiVarietyResponse;
import koicare.koiCareProject.entity.KoiVariety;
import koicare.koiCareProject.repository.KoiVarietyRepository;
import koicare.koiCareProject.service.KoiVarietyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/koivariety")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiVarietyController {

    @Autowired
    KoiVarietyService koiVarietyService;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping("create")
    public ResponseEntity createKoiVariety(@RequestBody KoiVarietyRequest request) {
        APIResponse<KoiVarietyResponse> apiResponse = new APIResponse<>();

        KoiVariety koiVariety = koiVarietyService.createKoiVariety(request);
        KoiVarietyResponse koiVarietyResponse = modelMapper.map(koiVariety, KoiVarietyResponse.class);

        apiResponse.setResult(koiVarietyResponse);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity getKoiVarieties() {
        List<KoiVariety> koiVarieties = koiVarietyService.getKoiVarieties();
        List<KoiVarietyResponse> koiVarietyResponses = koiVarieties.stream()
                .map(koiVariety -> modelMapper.map(koiVariety, KoiVarietyResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(koiVarietyResponses);
    }

    @GetMapping("{koiVarietyID}")
    public ResponseEntity getKoiVariety(@PathVariable("koiVarietyID") Long koiVarietyID) {
        KoiVarietyResponse koiVarietyResponse = modelMapper.map(koiVarietyService.getKoiVariety(koiVarietyID), KoiVarietyResponse.class);
        return ResponseEntity.ok(koiVarietyResponse);
    }


}
