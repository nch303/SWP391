package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiVarietyRequest;
import koicare.koiCareProject.dto.response.KoiVarietyResponse;
import koicare.koiCareProject.entity.KoiVariety;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.KoiVarietyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoiVarietyService {

    @Autowired
    private KoiVarietyRepository koiVarietyRepository;

    @Autowired
    private ModelMapper modelMapper;

    //tạo 1 Variety mới
    public KoiVariety createKoiVariety(KoiVarietyRequest request) {

        KoiVariety koiVariety = modelMapper.map(request, KoiVariety.class);

        return koiVarietyRepository.save(koiVariety);

    }

    //get tất cả koivariety có trong danh sách
    public List<KoiVariety> getKoiVarieties() {
        List<KoiVariety> koiVarieties = koiVarietyRepository.findAll();
        return koiVarieties;
    }

    //get koivariety bằng id
    public KoiVariety getKoiVariety(Long koiVarietyID) {
        KoiVariety koiVariety = koiVarietyRepository.getKoiVarietyByKoiVarietyID(koiVarietyID);
        if(koiVariety != null)
            return koiVariety;
        else{
            throw new AppException(ErrorCode.VARIETY_NOT_EXISTED);
        }
    }

}
