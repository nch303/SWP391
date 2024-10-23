package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.EmailDetail;
import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.dto.response.MemberResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.AccountRepository;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.repository.PondRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member updateMember(MemberCreationRequest request){

        Account account = authenticationService.getCurrentAccount();
        Member member = memberRepository.getMemberByAccount(account);
        member.setName(request.getName());
        member.setPhone(request.getPhone());

        if(!member.getEmail().equals(request.getEmail())){
            member.setEmail(request.getEmail());
            account.setEmail(request.getEmail());

            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setAccount(account);
            emailDetail.setSubject("You have changed your email!");
            emailDetail.setLink("http://103.90.227.68/");

            emailService.sendEmailUpdateMember(emailDetail);
        }


        accountRepository.save(account);
        return memberRepository.save(member);
    }
    //

}
