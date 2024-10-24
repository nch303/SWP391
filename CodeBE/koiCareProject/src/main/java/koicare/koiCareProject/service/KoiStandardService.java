package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiStandardFullRequest;
import koicare.koiCareProject.dto.request.KoiStandardRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiStandardResponse;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.KoiStandardRepository;
import koicare.koiCareProject.repository.KoiVarietyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class KoiStandardService {

    @Autowired
    private KoiStandardRepository koiStandardRepository;

    @Autowired
    private KoiVarietyRepository koiVarietyRepository;

    @Autowired
    ModelMapper modelMapper;


    // Lấy KoiStandard theo VarietyID và Period
    public KoiStandard getKoiStandardByVarietyAndPeriod(KoiStandardRequest request) {
        KoiStandard koiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod(koiVarietyRepository.getKoiVarietyByKoiVarietyID(request.getKoiVarietyID()),request.getPeriod());
        if (koiStandard != null)
            return koiStandard;
        else
            throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }

    // Lấy all KoiStandard
    public List<KoiStandard> getAllKoiStandard(){
        List<KoiStandard> koiStandards = koiStandardRepository.findAll();
        return koiStandards;
    }

    //lấy KoiStandard theo ID
    public KoiStandard getKoiStandardByID(long koiStandardID){
        KoiStandard koiStandard = koiStandardRepository.getKoiStandardBykoiStandID(koiStandardID);
        if(koiStandard != null){
            return koiStandard;
        } else throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }

    //update KoiStandard
    public KoiStandard updateKoiStandard(KoiStandardFullRequest request, long koiStandardID){
        KoiStandard koiStandard = koiStandardRepository.getKoiStandardBykoiStandID(koiStandardID);
        if(koiStandard != null){
            koiStandard = modelMapper.map(request, KoiStandard.class);
            koiStandard.setKoiStandID(koiStandardID);
            return koiStandardRepository.save(koiStandard);
        } else throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }

}
