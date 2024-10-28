package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.ChangePasswordRequest;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void changePassword(ChangePasswordRequest request){
        Account account = authenticationService.getCurrentAccount();
        if(passwordEncoder.matches(request.getOldPassword(), account.getPassword())) {
            account.setPassword(passwordEncoder.encode(request.getNewPassword()));
            accountRepository.save(account);
        } else {
            throw new AppException(ErrorCode.WRONG_PASSWORD);
        }
    }
}
