package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiStandardRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.repository.KoiStandardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class KoiStandardService {

    @Autowired
    KoiStandardRepository koiStandardRepository;

    @Autowired
    ModelMapper modelMapper;

    public KoiStandard createKoiStandard(@RequestBody KoiStandardRequest request){
        KoiStandard koiStandard = modelMapper.map(request, KoiStandard.class);

        return koiStandardRepository.save(koiStandard);
    }
}
