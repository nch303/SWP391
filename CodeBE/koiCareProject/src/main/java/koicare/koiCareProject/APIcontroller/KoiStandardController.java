package koicare.koiCareProject.APIcontroller;

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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("koistandard")
public class KoiStandardController {

    @Autowired
    KoiStandardService koiStandardService;

    @Autowired
    ModelMapper modelMapper;


    // Lấy KoiStandard
    @GetMapping("")
    public APIResponse<List<KoiStandardResponse>> getKoiStandards(){
        APIResponse<List<KoiStandardResponse>> response = new APIResponse<>();

        List<KoiStandard> koiStandards = koiStandardService.getKoiStandards();
        List<KoiStandardResponse> koiStandardResponses = koiStandards.stream()
                .map(koiStandard -> modelMapper.map(koiStandard, KoiStandardResponse.class))
                .collect(Collectors.toList());

        response.setResult(koiStandardResponses);
        return response;
    }

    // Lấy KoiStandard theo ID
    @GetMapping("{koiStandID}")
    public APIResponse<KoiStandard> getKoiStandard(@PathVariable("koiStandID")long koiStandID){
        APIResponse<KoiStandard> response =new APIResponse<>();

        response.setResult(koiStandardService.getKoiStandard(koiStandID));

        return response;
    }
}
