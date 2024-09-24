package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.PondCreationRequest;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.repository.PondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PondService {
    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Pond createPond(PondCreationRequest request) {

        Pond pond = new Pond();

        pond.setPondName(request.getPondName());
        pond.setMember(memberRepository.getMemberByMemberID(request.getMemberID()));

        return pondRepository.save(pond);
    }

}
