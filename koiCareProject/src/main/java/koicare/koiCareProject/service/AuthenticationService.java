package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.LoginRequest;
import koicare.koiCareProject.dto.request.MemberRegisterRequest;
import koicare.koiCareProject.dto.response.AccountResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

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

    public AccountResponse register(MemberRegisterRequest memberRegisterRequest) {
        Account account = modelMapper.map(memberRegisterRequest, Account.class);

        Account existedAccount = accountRepository.findAccountByUsername(memberRegisterRequest.getUsername());
        if (existedAccount == null) {
            try {
                String originPassword = account.getPassword();
                account.setPassword(passwordEncoder.encode(originPassword));

                Account newAccount = accountRepository.save(account);
                return modelMapper.map(account, AccountResponse.class);
            } catch (Exception e) {
                if (e.getMessage().contains(account.getUsername())) {
                    throw new AppException(ErrorCode.USERNAME_EXISTED);
                }
            }
        }else throw new AppException(ErrorCode.USERNAME_EXISTED);
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
            return modelMapper.map(account, AccountResponse.class);
        } catch (Exception e) {
            throw new AppException(ErrorCode.LOGIN_FAIL);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountByUsername(username);
    }
}
