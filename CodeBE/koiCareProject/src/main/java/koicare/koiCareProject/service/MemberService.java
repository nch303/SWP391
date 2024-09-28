package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.repository.PondRepository;
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
    public Member createMember(MemberCreationRequest request) {
        Member member = new Member();

        member.setMemberName(request.getMemberName());
        member.setMemberEmail(request.getMemberEmail());
        member.setMemberPhone(request.getMemberPhone());


        return memberRepository.save(member);
    }

    public List<Pond> getPondsByMemberId(Long memberId) {
        List<Pond> ponds = pondRepository.findAll();

        List<Pond> filteredPonds = new ArrayList<>();
        for (Pond pond : ponds) {
            if (pond.getMember().getMemberID() == memberId) {
                filteredPonds.add(pond);
            }
        }
        return filteredPonds;
    }
}
