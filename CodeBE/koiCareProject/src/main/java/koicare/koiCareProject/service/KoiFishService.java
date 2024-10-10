package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiFishRequest;
import koicare.koiCareProject.dto.response.PondResponse;
import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoiFishService {

    @Autowired
    KoiFishRepository koiFishRepository;

    @Autowired
    PondRepository pondRepository;

    @Autowired
    KoiVarietyRepository koiVarietyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    KoiReportRepository koiReportRepository;

    @Autowired
    PondService pondService;

    @Autowired
    KoiStatusRepository koiStatusRepository;

    //tạo cá koi
    public KoiFish createKoiFish(KoiFishRequest request) {

        KoiFish koiFish = new KoiFish();

        Pond pond = pondRepository.getPondByPondID(request.getPondID());
        PondResponse pondResponse = modelMapper.map(pondService.getPondById(request.getPondID()), PondResponse.class);

        if (pondResponse != null) {
            koiFish.setKoiSex(request.getKoiSex());
            koiFish.setKoiName(request.getKoiName());
            koiFish.setImage(request.getImage());
            koiFish.setBirthday(request.getBirthday());
            koiFish.setKoiVariety(koiVarietyRepository.getKoiVarietyByKoiVarietyID(request.getKoiVarietyID()));
            koiFish.setPond(modelMapper.map(pondResponse, Pond.class));

            Account account = authenticationService.getCurrentAccount();
            Member member = memberRepository.getMemberByAccount(account);
            koiFish.setMember(member);

            //sau khi tạo cá sẽ tăng số lượng cá trong hồ lên 1
            //Pond pond = pondRepository.getPondByPondID(request.getPondID());
            pond.setAmountFish(pond.getAmountFish() + 1);
            pondRepository.save(pond);

            koiFishRepository.save(koiFish);

//            KoiReport koiReport =  new KoiReport();
//            koiReport.setWeight(1);
//            koiReport.setLength(1);
//            koiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(1));
//            koiReport.setKoiFish(koiFish);
//            koiReportRepository.save(koiReport);


            return koiFish;
        } else {
            throw new AppException(ErrorCode.POND_NOT_EXISTED);
        }


    }

    //lấy lên danh sách cá koi theo MemberID
    public List<KoiFish> getKoiFishes() {

        Account account = authenticationService.getCurrentAccount();
        Member member = memberRepository.getMemberByAccount(account);

        return koiFishRepository.findAllByMember(member);
    }

    //lấy cá koi theo koiFishID
    public KoiFish getKoiFish(long koiFishID) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(koiFishID);
        if (koiFish != null)
            return koiFish;
        else
            throw new AppException(ErrorCode.KOIFISH_NOT_EXISTED);
    }

    //update cá koi
    public KoiFish updateKoiFish(long koiFishID, KoiFishRequest request) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(koiFishID);
        if (koiFish != null) {
            koiFish = modelMapper.map(request, KoiFish.class);
            koiFish.setKoiFishID(koiFishID);

            Account account = authenticationService.getCurrentAccount();
            Member member = memberRepository.getMemberByAccount(account);
            koiFish.setMember(member);

            return koiFishRepository.save(koiFish);
        } else
            throw new AppException(ErrorCode.KOIFISH_NOT_EXISTED);
    }

    //xóa cá khỏi danh sách
    public void deleteKoiFish(long koiFishID) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(koiFishID);


        if (koiFish != null) {
            List<KoiReport> koiReports = koiReportRepository.getKoiReportsByKoiFish(koiFish);
            if (koiReports != null) {
                for (KoiReport koiReport : koiReports) {
                    koiReportRepository.delete(koiReport);
                }
            }
            Pond pond = pondRepository.getPondByPondID(koiFish.getPond().getPondID());
            pond.setAmountFish(pond.getAmountFish() - 1);
            koiFishRepository.deleteById(koiFishID);
        } else
            throw new AppException(ErrorCode.KOIFISH_NOT_EXISTED);
    }
}
