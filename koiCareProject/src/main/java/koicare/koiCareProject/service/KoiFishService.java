package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiFishRequest;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiVariety;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.repository.KoiVarietyRepository;
import koicare.koiCareProject.repository.PondRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    //tạo cá koi
    public KoiFish createKoiFish(KoiFishRequest request) {

        KoiFish koiFish = modelMapper.map(request, KoiFish.class);
        koiFish.setKoiVariety(koiVarietyRepository.getKoiVarietyByKoiVarietyID(request.getKoiVarietyID()));
        koiFish.setPond(pondRepository.getPondByPondID(request.getPondID()));

        //sau khi tạo cá sẽ tăng số lượng cá trong hồ lên 1
        Pond pond = pondRepository.getPondByPondID(request.getPondID());
        pond.setAmountFish(pond.getAmountFish() + 1);

        return koiFishRepository.save(koiFish);
    }

    //lấy lên danh sách cá koi
    public List<KoiFish> getKoiFishes() {
        return koiFishRepository.findAll();
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
            koiFish =  modelMapper.map(request, KoiFish.class);
            koiFish.setKoiFishID(koiFishID);
            return koiFishRepository.save(koiFish);
        } else
            throw new AppException(ErrorCode.KOIFISH_NOT_EXISTED);
    }

    //xóa cá khỏi danh sách
    public void deleteKoiFish(long koiFishID){
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(koiFishID);
        if (koiFish != null) {
            koiFishRepository.deleteById(koiFishID);
        }else
            throw new AppException(ErrorCode.KOIFISH_NOT_EXISTED);
    }
}
