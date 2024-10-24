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

import java.util.Date;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ShopRepository shopRepository;

    //Member Register
    public AccountResponse register(RegisterRequest registerRequest) {

        Account account = new Account();
        account.setUsername(registerRequest.getUsername());
        account.setPassword(registerRequest.getPassword());
        account.setRole(registerRequest.getRole());
        account.setEmail(registerRequest.getEmail());
        account.setStatus(true);
        account.setBalance(0);
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

                    EmailDetail emailDetail = new EmailDetail();
                    emailDetail.setAccount(newAccount);
                    emailDetail.setSubject("Welcome to Sun Side Koi Care!");
                    emailDetail.setLink("http://103.90.227.68/");
                    emailService.sendEmail(emailDetail);
                } else {
                    Shop shop = modelMapper.map(registerRequest, Shop.class);
                    shop.setAccount(newAccount);
                    shopRepository.save(shop);

                    EmailDetail emailDetail = new EmailDetail();
                    emailDetail.setAccount(newAccount);
                    emailDetail.setSubject("Welcome to Sun Side Koi Care Shop!");
                    emailDetail.setLink("http://103.90.227.68/shop");
                    emailService.sendEmailShop(emailDetail);
                }
                
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

            //kiểm tra xem member còn hạn premium không
            if(account.getRole().toString().equals("MEMBER")){
                Member member = memberRepository.getMemberByAccount(account);
                if(member.getExpiredDate() == null){
                    member.setExpiredDate(new Date());
                    member.setMemberID(member.getMemberID());
                    memberRepository.save(member);
                }
                if(member.getExpiredDate().before(new Date())){
                    member.setPremiumStatus(0);
                }
                else member.setPremiumStatus(1);
                member.setMemberID(member.getMemberID());
                memberRepository.save(member);
            }

            //kiểm tra tài khoản có bị banned không
            if (account.isStatus()) {
                AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);
                accountResponse.setToken(tokenService.generateToken(account));
                return accountResponse;
            } else throw new AppException(ErrorCode.USER_NOT_EXISTED);

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
    public Account deleteAccount(long accountID) {
        Account account = accountRepository.findAccountByAccountID(accountID);
        account.setStatus(false);

        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setAccount(account);
        emailDetail.setSubject("Your account have been banned!");
        emailDetail.setLink("");

        emailService.sendEmailBannedAccount(emailDetail);

        return accountRepository.save(account);
    }

    //khôi phục account bị xóa
    public Account restoreAccount(long accountID) {
        Account account = accountRepository.findAccountByAccountID(accountID);
        account.setStatus(true);

        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setAccount(account);
        emailDetail.setSubject("Your account have been restore!");
        if(account.getRole().toString().equals("MEMBER")) {
            emailDetail.setLink("http://103.90.227.68/");
        }else{
            emailDetail.setLink("http://103.90.227.68/shop");
        }

        emailService.sendEmailRestoreAccount(emailDetail);

        return accountRepository.save(account);
    }

    public void forgotPassword(ForgotPasswordRequest request) {
        Account account = accountRepository.findAccountByEmail(request.getEmail());

        if (account == null) {
            throw new AppException(ErrorCode.ACCOUNT_IS_NOT_EXISTED);
        } else {
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setAccount(account);
            emailDetail.setSubject("Reset Password");
            emailDetail.setLink("https://koisale.com/?token=" + tokenService.generateToken(account));

            emailService.sendEmail(emailDetail);
        }
    }

    public void resetPassword(ResetPasswordRequest request) {
        Account account = getCurrentAccount();
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        accountRepository.save(account);
    }

}

