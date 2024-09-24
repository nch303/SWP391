package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(MemberCreationRequest request) {
        Member member = new Member();

        member.setMemberName(request.getMemberName());
        member.setMemberEmail(request.getMemberEmail());
        member.setMemberPhone(request.getMemberPhone());

        return memberRepository.save(member);
    }
}
