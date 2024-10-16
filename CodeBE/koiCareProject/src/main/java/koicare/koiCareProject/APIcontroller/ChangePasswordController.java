package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.ChangePasswordRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/changepassword")
@SecurityRequirement(name = "api")
public class ChangePasswordController {

    @Autowired
    ChangePasswordService changePasswordService;

    @PutMapping
    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest request){
        APIResponse response = new APIResponse();
        changePasswordService.changePassword(request);
        response.setResult("Change Password Successfully");
        return ResponseEntity.ok(response);
    }
}
