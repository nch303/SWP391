package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.ExpertModeRequest;
import koicare.koiCareProject.dto.request.KoiFoodRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiFoodListResponse;
import koicare.koiCareProject.service.KoiFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/koifood")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiFoodController {

    @Autowired
    private KoiFoodService koiFoodService;

    @PostMapping("")
    public ResponseEntity calculateFood(@RequestBody KoiFoodRequest request){
        APIResponse<Double> response = new APIResponse<>();

        response.setResult(koiFoodService.calculateFoodInPond(request.getPondID(),request.getTemperature(), request.getLevel()));

        return ResponseEntity.ok(response);
    }

    //viết 1 danh sách các con cá trong hồ với lượng thức ăn cho từng con cá
    @PostMapping("koilist")
    public  ResponseEntity calculateFoodEachKoiFish(@RequestBody KoiFoodRequest request){
        APIResponse<List<KoiFoodListResponse>> response = new APIResponse<>();
        response.setResult(koiFoodService.calculateFoodForKoiList(request.getPondID(),request.getTemperature(),request.getLevel()));
        return ResponseEntity.ok(response);
    }

    //ExpertMode
    @PostMapping("expertmode")
    public ResponseEntity expertMode(@RequestBody ExpertModeRequest request){
        APIResponse<Double> response =  new APIResponse<>();
        response.setResult(koiFoodService.expertMode(request));
        return ResponseEntity.ok(response);
    }
}
