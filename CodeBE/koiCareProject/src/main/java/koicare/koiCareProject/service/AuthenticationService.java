package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.*;
import koicare.koiCareProject.dto.response.AccountResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Shop;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.AccountRepository;
import koicare.koiCareProject.repository.AuthorizationRepository;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.repository.ShopRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmailService emailService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthorizationRepository authorizationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ShopRepository shopRepository;

    //Member Register
    public AccountResponse register(RegisterRequest registerRequest) {

        Account account = new Account();
        account.setUsername(registerRequest.getUsername());
        account.setPassword(registerRequest.getPassword());
        account.setRole(registerRequest.getRole());
        account.setEmail(registerRequest.getEmail());
        account.setStatus(true);
        Account existedAccount = accountRepository.findAccountByUsername(registerRequest.getUsername());
        if (existedAccount == null) {
            try {
                String originPassword = account.getPassword();
                account.setPassword(passwordEncoder.encode(originPassword));

                Account newAccount = accountRepository.save(account);

                String role = (String) account.getRole().toString();
                if (role.equals("MEMBER")) {
                    Member member = modelMapper.map(registerRequest, Member.class);
                    member.setAccount(newAccount);
                    memberRepository.save(member);
                } else {
                    Shop shop = modelMapper.map(registerRequest, Shop.class);
                    shop.setAccount(newAccount);
                    shopRepository.save(shop);
                }


                EmailDetail emailDetail = new EmailDetail();
                emailDetail.setAccount(newAccount);
                emailDetail.setSubject("Welcome to Sun Side Koi Care!");
                emailDetail.setLink("https://koisale.com/");

                emailService.sendEmail(emailDetail);

                return modelMapper.map(account, AccountResponse.class);
            } catch (Exception e) {
                e.printStackTrace(); // In chi tiết lỗi ra console
                if (e.getMessage().contains(account.getUsername())) {
                    throw new AppException(ErrorCode.USERNAME_EXISTED);
                }
            }
        } else throw new AppException(ErrorCode.USERNAME_EXISTED);
        return modelMapper.map(account, AccountResponse.class);
    }


    public List<Account> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public AccountResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            ));
            Account account = (Account) authentication.getPrincipal();
            AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);
            accountResponse.setToken(tokenService.generateToken(account));
            return accountResponse;
        } catch (Exception e) {
            throw new AppException(ErrorCode.LOGIN_FAIL);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountByUsername(username);
    }

    //gọi ra account đang thao tác với API
    public Account getCurrentAccount() {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        account.setMember(memberRepository.getMemberByAccount(account));
        return authorizationRepository.findAccountByAccountID(account.getAccountID());
    }

    //xóa account bằng cách setStatus bằng 0
    public Account deleteAccount(long accountID){
        Account account = accountRepository.findAccountByAccountID(accountID);
        account.setStatus(false);

        return accountRepository.save(account);
    }

    //khôi phục account bị xóa
    public Account restoreAccount(long accountID){
        Account account = accountRepository.findAccountByAccountID(accountID);
        account.setStatus(true);

        return accountRepository.save(account);
    }

    public void forgotPassword(ForgotPasswordRequest request){
        Account account = accountRepository.findAccountByEmail(request.getEmail());

        if(account == null){
            throw new AppException(ErrorCode.ACCOUNT_IS_NOT_EXISTED);
        }
        else {
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setAccount(account);
            emailDetail.setSubject("Reset Password");
            emailDetail.setLink("https://koisale.com/?token=" + tokenService.generateToken(account));

            emailService.sendEmail(emailDetail);
        }
    }

    public void resetPassword(ResetPasswordRequest request){
        Account account = getCurrentAccount();
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        accountRepository.save(account);
    }
}

