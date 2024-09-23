package koicare.koiCareProject.APIcontroller;


import koicare.koiCareProject.dto.request.MemberRegisterRequest;
import koicare.koiCareProject.dto.response.AccountResponse;
import koicare.koiCareProject.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("api")
public class AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("register")
    public EntityResponse register(@RequestBody MemberRegisterRequest memberRegisterRequest){
        AccountResponse newAccount = authenticationService.register(memberRegisterRequest);
    }
}
