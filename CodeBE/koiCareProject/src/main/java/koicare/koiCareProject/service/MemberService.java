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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmailService emailService;

    public Member updateMember(MemberCreationRequest request){

        Account account = authenticationService.getCurrentAccount();
        Member member = memberRepository.getMemberByAccount(account);
        member.setName(request.getMemberName());
        member.setPhone(request.getMemberPhone());
        member.setEmail(request.getMemberEmail());
        account.setEmail(request.getMemberEmail());

        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setAccount(account);
        emailDetail.setSubject("You have changed your email!");
        emailDetail.setLink("https://koisale.com/");

        emailService.sendEmail(emailDetail);

        accountRepository.save(account);
        return memberRepository.save(member);
    }

}
