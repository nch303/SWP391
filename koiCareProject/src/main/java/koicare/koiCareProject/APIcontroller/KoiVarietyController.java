package koicare.koiCareProject.APIcontroller;

import koicare.koiCareProject.dto.request.KoiVarietyRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiVarietyResponse;
import koicare.koiCareProject.entity.KoiVariety;
import koicare.koiCareProject.repository.KoiVarietyRepository;
import koicare.koiCareProject.service.KoiVarietyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class KoiVarietyController {

    @Autowired
    KoiVarietyService koiVarietyService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("createkoivariety")
    APIResponse<KoiVariety> createKoiVariety(@RequestBody KoiVarietyRequest request) {
        APIResponse<KoiVariety> apiResponse = new APIResponse<>();

        apiResponse.setResult(koiVarietyService.createKoiVariety(request));

        return apiResponse;
    }
}
