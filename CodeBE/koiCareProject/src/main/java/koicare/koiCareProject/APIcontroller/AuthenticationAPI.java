package koicare.koiCareProject.APIcontroller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import koicare.koiCareProject.dto.request.ForgotPasswordRequest;
import koicare.koiCareProject.dto.request.LoginRequest;
import koicare.koiCareProject.dto.request.RegisterRequest;
import koicare.koiCareProject.dto.request.ResetPasswordRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.AccountResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.repository.AccountRepository;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.repository.ShopRepository;
import koicare.koiCareProject.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class AuthenticationAPI {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AccountRepository accountRepository;


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
        List<AccountResponse> accountResponses = accounts.stream()
                .map(account -> {
                    AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);

                    if (account.getRole().toString().contains("SHOP")) {
                        accountResponse.setName(shopRepository.getShopByAccount(account).getName());
                        accountResponse.setPhone(shopRepository.getShopByAccount(account).getPhone());
                        accountResponse.setNumberOfPosts(shopRepository.getShopByAccount(account).getNumberOfPosts());
                    } else if (account.getRole().toString().contains("MEMBER")) {
                        accountResponse.setName(memberRepository.getMemberByAccount(account).getName());
                        accountResponse.setPhone(memberRepository.getMemberByAccount(account).getPhone());
                        accountResponse.setPremiumStatus(memberRepository.getMemberByAccount(account).getPremiumStatus());
                        accountResponse.setExpiredDate(memberRepository.getMemberByAccount(account).getExpiredDate());
                    }

                    return accountResponse;
                })
                .toList();
        return ResponseEntity.ok(accountResponses);
    }

    //API lấy thông tin của account theo ID
    @GetMapping("account/{accountID}")
    public ResponseEntity getAccountByAccountID(@PathVariable long accountID){
        APIResponse<AccountResponse> response = new APIResponse<>();
        Account account = accountRepository.findAccountByAccountID(accountID);
        AccountResponse accountResponse = modelMapper.map(account,AccountResponse.class);
        if (account.getRole().toString().contains("SHOP")) {
            accountResponse.setName(shopRepository.getShopByAccount(account).getName());
            accountResponse.setPhone(shopRepository.getShopByAccount(account).getPhone());
            accountResponse.setNumberOfPosts(shopRepository.getShopByAccount(account).getNumberOfPosts());
        } else if (account.getRole().toString().contains("MEMBER")) {
            accountResponse.setName(memberRepository.getMemberByAccount(account).getName());
            accountResponse.setPhone(memberRepository.getMemberByAccount(account).getPhone());
            accountResponse.setPremiumStatus(memberRepository.getMemberByAccount(account).getPremiumStatus());
            accountResponse.setExpiredDate(memberRepository.getMemberByAccount(account).getExpiredDate());
        }

        response.setResult(accountResponse);
        return ResponseEntity.ok(response);
    }

    //API lấy thông tin của account hiện tại
    @GetMapping("currentAccount")
    public ResponseEntity getCurrentAccount() {
        Account account = authenticationService.getCurrentAccount();
        AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);
        if (account.getRole().toString().contains("SHOP")) {
            accountResponse.setName(shopRepository.getShopByAccount(account).getName());
            accountResponse.setPhone(shopRepository.getShopByAccount(account).getPhone());
            accountResponse.setNumberOfPosts(shopRepository.getShopByAccount(account).getNumberOfPosts());
        } else if (account.getRole().toString().contains("MEMBER")) {
            accountResponse.setName(memberRepository.getMemberByAccount(account).getName());
            accountResponse.setPhone(memberRepository.getMemberByAccount(account).getPhone());
            accountResponse.setPremiumStatus(memberRepository.getMemberByAccount(account).getPremiumStatus());
            accountResponse.setExpiredDate(memberRepository.getMemberByAccount(account).getExpiredDate());
        }


        return ResponseEntity.ok(accountResponse);
    }


    @PostMapping("login")
    public ResponseEntity register(@Valid @RequestBody LoginRequest loginRequest) {
        AccountResponse newAccount = authenticationService.login(loginRequest);
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
