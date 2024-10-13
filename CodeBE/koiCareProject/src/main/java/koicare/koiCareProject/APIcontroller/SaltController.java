package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.SaltRequest;
import koicare.koiCareProject.dto.request.SaltRequest2;
import koicare.koiCareProject.service.SaltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/salt")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class SaltController {

    @Autowired
    private SaltService saltService;

    @PostMapping("calculateSalt-per-water-change")
    public ResponseEntity calculateSaltPerWaterChange(@RequestBody SaltRequest request){
        double salt = saltService.calculatorSaltPerWaterChange(request);
        return ResponseEntity.ok(salt);
    }

    @PostMapping("calculateSalt")
    public ResponseEntity calculateSalt(@RequestBody SaltRequest2 request){
        double salt = saltService.calculatorSalt(request);
        return ResponseEntity.ok(salt);
    }

    @PostMapping("per-water-change")
    public ResponseEntity perWaterChange(@RequestBody SaltRequest request){
        long perchange = saltService.calculatePerWaterChange(request);
        return ResponseEntity.ok(perchange);
    }

}
