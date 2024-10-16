package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.SaltRequest;
import koicare.koiCareProject.dto.request.SaltRequest2;
import koicare.koiCareProject.repository.PondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaltService {

    @Autowired
    private PondRepository pondRepository;

    public double calculatorSaltPerWaterChange(SaltRequest request) {
        long volume = pondRepository.getPondByPondID(request.getPondID()).getVolume();
        double salt = volume * (request.getExpectSalt() / 100) * (request.getWaterchangePer() / 100);

        return Math.round(salt * 100.0) / 100.0;

    }

    public double calculatorSalt(SaltRequest2 request) {
        long volume = pondRepository.getPondByPondID(request.getPondID()).getVolume();
        if (request.getExpectSalt() > request.getCurrentSalt()) {
            double salt = volume * (request.getExpectSalt() - request.getCurrentSalt()) / 100;
            return Math.round(salt * 100.0) / 100.0;
        } else return 0;
    }

    public long calculatePerWaterChange(SaltRequest request) {
        long volume = pondRepository.getPondByPondID(request.getPondID()).getVolume();
        if(request.getExpectSalt() < 0.01){
            request.setExpectSalt(0.009);
        }

        double perChange = Math.log(request.getExpectSalt() / request.getCurrentSalt()) / Math.log(1 - request.getWaterchangePer() / 100);

        long roundedValue = (long) Math.ceil(perChange); // Làm tròn lên
        if(roundedValue <= 0) roundedValue = 1;
        return roundedValue;

    }

}
