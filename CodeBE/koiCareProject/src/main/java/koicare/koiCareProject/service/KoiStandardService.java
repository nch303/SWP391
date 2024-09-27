package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiStandardRequest;
import koicare.koiCareProject.dto.response.APIResponse;
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
    KoiStandardRepository koiStandardRepository;

    @Autowired
    KoiVarietyRepository koiVarietyRepository;

    @Autowired
    ModelMapper modelMapper;


    // Lấy KoiStandard theo ID
    public KoiStandard getKoiStandard(KoiStandardRequest request) {
        KoiStandard koiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod(koiVarietyRepository.getKoiVarietyByKoiVarietyID(request.getKoiVarietyID()),request.getPeriod());
        if (koiStandard != null)
            return koiStandard;
        else
            throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }

}
