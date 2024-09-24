package koicare.koiCareProject.APIcontroller;

import koicare.koiCareProject.dto.request.PondCreationRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.service.PondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pond")
public class PondController {
    @Autowired
    private PondService pondService;

    @PostMapping
    public APIResponse<Pond> createPond(@RequestBody PondCreationRequest request) {

        APIResponse<Pond> response = new APIResponse<>();
        response.setResult(pondService.createPond(request));
        return response;
    }



}
