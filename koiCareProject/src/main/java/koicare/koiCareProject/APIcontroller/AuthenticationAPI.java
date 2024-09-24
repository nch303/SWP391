package koicare.koiCareProject.APIcontroller;


import jakarta.validation.Valid;
import koicare.koiCareProject.dto.request.LoginRequest;
import koicare.koiCareProject.dto.request.MemberRegisterRequest;
import koicare.koiCareProject.dto.response.AccountResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("api")
public class AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;

    //API register
    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody MemberRegisterRequest memberRegisterRequest) {
        AccountResponse newAccount = authenticationService.register(memberRegisterRequest);
        return ResponseEntity.ok(newAccount);
    }


    //API lấy thông tin account
    @GetMapping("account")
    public ResponseEntity getAllAccount() {
        List<Account> accounts = authenticationService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("login")
    public ResponseEntity register(@Valid @RequestBody LoginRequest loginRequest){
        AccountResponse newAccount =  authenticationService.login(loginRequest);
        return ResponseEntity.ok(newAccount);
    }



}
