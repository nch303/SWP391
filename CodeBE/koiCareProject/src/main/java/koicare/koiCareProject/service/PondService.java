package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiFishRequest;
import koicare.koiCareProject.dto.request.PondCreationRequest;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.repository.PondRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PondService {
    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationService authenticationService;

    //tạo Pond
    public Pond createPond(PondCreationRequest request) {

        Pond pond = new Pond();

        pond.setPondName(request.getPondName());
        pond.setPondImage(request.getPondImage());
        pond.setArea(request.getArea());
        pond.setDepth(request.getDepth());
        pond.setVolume(request.getVolume());
        pond.setPumpingCapacity(request.getPumpingCapacity());
        pond.setSkimmerCount(request.getSkimmerCount());
        pond.setDrainCount(request.getDrainCount());

        Account account = authenticationService.getCurrentAccount();
        Member member = memberRepository.getMemberByAccount(account);

        pond.setMember(member);

        return pondRepository.save(pond);
    }

    //Lấy danh sách Pond theo MemberID
    public List<Pond> getAllPonds() {

        Account account = authenticationService.getCurrentAccount();
        Member member = memberRepository.getMemberByAccount(account);

        return pondRepository.findAllByMember(member);
    }

    //lấy Pond theo ID
    public Pond getPondById(Long pondID) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        if (pond == null) {
            throw new AppException(ErrorCode.POND_NOT_EXISTED);
        } else return pond;
    }


    //Update Pond
    public Pond updatePond(long pondID, PondCreationRequest request) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        if (pond != null) {
            pond.setPondName(request.getPondName());
            pond.setPondImage(request.getPondImage());
            pond.setArea(request.getArea());
            pond.setDepth(request.getDepth());
            pond.setVolume(request.getVolume());
            pond.setDrainCount(request.getDrainCount());
            pond.setSkimmerCount(request.getSkimmerCount());
            pond.setPumpingCapacity(request.getPumpingCapacity());

            Account account = authenticationService.getCurrentAccount();
            Member member = memberRepository.getMemberByAccount(account);
            pond.setMember(member);

            pond.setPondID(pondID);
            return pondRepository.save(pond);
        } else
            throw new AppException(ErrorCode.POND_NOT_EXISTED);
    }

    //delete Pond
    public void deletePond(long pondID) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        if (pond == null) {
            throw new AppException(ErrorCode.POND_NOT_EXISTED);
        } else {
            pondRepository.deleteById(pondID);
        }
    }

}
