package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiStandardRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.KoiStandardRepository;
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
    ModelMapper modelMapper;

    // Lấy KoiStandard
    public List<KoiStandard> getKoiStandards() {
        return koiStandardRepository.findAll();
    }

    // Lấy KoiStandard theo ID
    public KoiStandard getKoiStandard(long koiStandID) {
        KoiStandard koiStandard = koiStandardRepository.getKoiStandardByKoiStandID(koiStandID);
        if (koiStandard != null)
            return koiStandard;
        else
            throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }

}
