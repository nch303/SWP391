package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.MemberRegisterRequest;
import koicare.koiCareProject.dto.response.AccountResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.function.EntityResponse;

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
        try {
            String originPassword = account.getPassword();
            account.setPassword(passwordEncoder.encode(originPassword));

            Account newAccount = accountRepository.save(account);
            return modelMapper.map(account, AccountResponse.class);
        } catch (Exception e) {

        }
        return null;
    }
}
