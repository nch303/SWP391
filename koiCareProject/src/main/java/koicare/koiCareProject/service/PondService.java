package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.PondCreationRequest;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.repository.PondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PondService {
    @Autowired
    private PondRepository pondRepository;

    public Pond createPond(PondCreationRequest request) {

        Pond pond = new Pond();

        pond.setPondName(request.getPondName());
//        pond.setArea(request.getArea());
//        pond.setDrainCount(request.getDrainCount());
//        pond.setPumpingCapacity(request.getPumpingCapacity());
//        pond.setDepth(request.getDepth());
//        pond.setVolume(request.getVolume());
//        pond.setSkimmerCount(request.getSkimmerCount());
        return pondRepository.save(pond);
    }

}
