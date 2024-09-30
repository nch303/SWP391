package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.WaterStandardRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.WaterReportResponse;
import koicare.koiCareProject.dto.response.WaterStandardResponse;
import koicare.koiCareProject.service.WaterStandardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "api")
@RequestMapping("waterstandard")
public class WaterStandardController {

    @Autowired
    private WaterStandardService waterStandardService;

    @Autowired
    ModelMapper modelMapper;
    @PutMapping("update")
    public APIResponse<WaterStandardResponse> updateWaterStandard(@RequestBody WaterStandardRequest request){
        APIResponse<WaterStandardResponse> response = new APIResponse<>();

        WaterStandardResponse waterStandardResponse = modelMapper.map
                (waterStandardService.updateWaterStandard(request), WaterStandardResponse.class);

        response.setResult(waterStandardResponse);
        return response;
    }
}
