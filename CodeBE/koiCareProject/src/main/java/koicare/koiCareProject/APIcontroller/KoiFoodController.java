package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.KoiFoodRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.service.KoiFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/koifood")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiFoodController {

    @Autowired
    KoiFoodService koiFoodService;

    @PostMapping("")
    public ResponseEntity calculateFood(@RequestBody KoiFoodRequest request){
        APIResponse<Double> response = new APIResponse<>();

        response.setResult(koiFoodService.calculateFoodInPond(request.getPondID(),request.getTemperature()));

        return ResponseEntity.ok(response);
    }
}
