package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiStatusResponse;
import koicare.koiCareProject.entity.KoiStatus;
import koicare.koiCareProject.service.KoiStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/koistatus")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiStatusController {

    @Autowired
    KoiStatusService koiStatusService;

    @Autowired
    ModelMapper modelMapper;

    //lấy tất cả các koiStatus trong danh sách
    @GetMapping("")
    public APIResponse<List<KoiStatusResponse>> getKoiStatuses(){
        APIResponse<List<KoiStatusResponse>> response = new APIResponse<>();

        List<KoiStatus> koiStatuses = koiStatusService.getKoiStatuses();
        List<KoiStatusResponse> koiStatusResponses = koiStatuses.stream()
                .map(koiStatus -> modelMapper.map(koiStatus, KoiStatusResponse.class))
                .collect(Collectors.toList());

        response.setResult(koiStatusResponses);
        return response;
    }

    //lấy koiStatus bằng ID
    @GetMapping("/{koiStatusID}")
    public APIResponse<KoiStatusResponse> getKoiStatus(@PathVariable("koiStatusID") long koiStatusID){
        APIResponse<KoiStatusResponse> response = new APIResponse<>();

        KoiStatus koiStatus = koiStatusService.getKoiStatus(koiStatusID);
        response.setResult(modelMapper.map(koiStatus, KoiStatusResponse.class));
        return response;
    }
}
