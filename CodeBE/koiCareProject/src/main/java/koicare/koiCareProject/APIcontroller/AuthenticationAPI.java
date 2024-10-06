package koicare.koiCareProject.APIcontroller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import koicare.koiCareProject.dto.request.ForgotPasswordRequest;
import koicare.koiCareProject.dto.request.LoginRequest;
import koicare.koiCareProject.dto.request.RegisterRequest;
import koicare.koiCareProject.dto.request.ResetPasswordRequest;
import koicare.koiCareProject.dto.response.AccountResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.repository.ShopRepository;
import koicare.koiCareProject.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    MemberRepository memberRepository;

    //API register
    @PostMapping("register")
    public ResponseEntity memberregister(@Valid @RequestBody RegisterRequest registerRequest) {
        AccountResponse newAccount = authenticationService.register(registerRequest);
        return ResponseEntity.ok(newAccount);
    }


    //API lấy thông tin tất cả account
    @GetMapping("account")
    public ResponseEntity getAllAccount() {
        List<Account> accounts = authenticationService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }

    //API lấy thông tin của account hiện tại
    @GetMapping("currentAccount")
    public ResponseEntity getCurrentAccount(){
        Account account = authenticationService.getCurrentAccount();
        AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);
        if(account.getRole().toString().contains("SHOP")){
           accountResponse.setName(shopRepository.getShopByAccount(account).getName());
           accountResponse.setPhone(shopRepository.getShopByAccount(account).getPhone());
        } else if (account.getRole().toString().contains("MEMBER")) {
            accountResponse.setName(memberRepository.getMemberByAccount(account).getName());
            accountResponse.setPhone(memberRepository.getMemberByAccount(account).getPhone());
        }


        return ResponseEntity.ok(accountResponse);
    }


    @PostMapping("login")
    public ResponseEntity register(@Valid @RequestBody LoginRequest loginRequest){
        AccountResponse newAccount =  authenticationService.login(loginRequest);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("forgot-password")
    public ResponseEntity forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authenticationService.forgotPassword(request);
        return ResponseEntity.ok("Forgot Password successfully.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest request) {

        authenticationService.resetPassword(request);
        return ResponseEntity.ok("Reset Password successfully.");
    }

}
