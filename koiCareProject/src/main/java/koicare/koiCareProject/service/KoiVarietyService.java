package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiVarietyRequest;
import koicare.koiCareProject.dto.response.KoiVarietyResponse;
import koicare.koiCareProject.entity.KoiVariety;
import koicare.koiCareProject.repository.KoiVarietyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KoiVarietyService {

    @Autowired
    KoiVarietyRepository koiVarietyRepository;

    @Autowired
    ModelMapper modelMapper;

    public KoiVariety createKoiVariety(KoiVarietyRequest request){

        KoiVariety koiVariety = modelMapper.map(request, KoiVariety.class);

        return koiVarietyRepository.save(koiVariety);

    }
}
