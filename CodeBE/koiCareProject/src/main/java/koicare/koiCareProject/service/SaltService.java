package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.SaltRequest;
import koicare.koiCareProject.dto.request.SaltRequest2;
import koicare.koiCareProject.repository.PondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaltService {

    @Autowired
    PondRepository pondRepository;

    public double calculatorSaltPerWaterChange(SaltRequest request) {
        long volume = pondRepository.getPondByPondID(request.getPondID()).getVolume();
        double salt = volume * (request.getExpectSalt() - request.getCurrentSalt()) / 100 * ((double) request.getWaterchangePer() / volume);
        return salt;

    }

    public double calculatorSalt(SaltRequest2 request) {
        long volume = pondRepository.getPondByPondID(request.getPondID()).getVolume();
        if (request.getExpectSalt() > request.getCurrentSalt()) {
            double salt = volume * (request.getExpectSalt() - request.getCurrentSalt()) / 100;
            return salt;
        }else return 0;
    }

    public long calculatePerWaterChange(SaltRequest request){
        long volume = pondRepository.getPondByPondID(request.getPondID()).getVolume();
        double perChange = volume * (request.getCurrentSalt() - request.getExpectSalt()) * 4 / request.getWaterchangePer();

        long roundedValue = Math.round(perChange);
        return roundedValue;
    }

}
