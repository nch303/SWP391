package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.WaterReportRequest;
import koicare.koiCareProject.dto.request.WaterStandardRequest;
import koicare.koiCareProject.entity.WaterStandard;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.WaterStandardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterStandardService {

    @Autowired
    private WaterStandardRepository waterStandardRepository;

    @Autowired
    ModelMapper modelMapper;

    public WaterStandard updateWaterStandard(WaterStandardRequest request) {


        List<WaterStandard> list = waterStandardRepository.findAll();
        if (list.size() <= 0) {
            WaterStandard waterStandard = new WaterStandard();

            waterStandard.setMinTempStandard(0);
            waterStandard.setMaxTempStandard(0);

            waterStandard.setMaxOxygenStandard(0);
            waterStandard.setMinOxygenStandard(0);

            waterStandard.setMax_pH_Standard(0);
            waterStandard.setMin_pH_Standard(0);

            waterStandard.setMinHardnessStandard(0);
            waterStandard.setMaxHardnessStandard(0);

            waterStandard.setMaxAmmoniaStandard(0);
            waterStandard.setMinAmmoniaStandard(0);

            waterStandard.setMaxNitriteStandard(0);
            waterStandard.setMinNitriteStandard(0);

            waterStandard.setMaxNitrateStandard(0);
            waterStandard.setMinNitrateStandard(0);

            waterStandard.setMaxCarbonateStandard(0);
            waterStandard.setMinCarbonateStandard(0);

            waterStandard.setMaxCarbonDioxideStandard(0);
            waterStandard.setMinCarbonDioxideStandard(0);

            waterStandard.setMaxSaltStandard(0);
            waterStandard.setMinSaltStandard(0);

            waterStandardRepository.save(waterStandard);

        }

        WaterStandard waterStandard = waterStandardRepository.findByWaterStandardId(1);

        waterStandard.setMinTempStandard(request.getMinTempStandard());
        waterStandard.setMaxTempStandard(request.getMaxTempStandard());

        waterStandard.setMaxOxygenStandard(request.getMaxOxygenStandard());
        waterStandard.setMinOxygenStandard(request.getMinOxygenStandard());

        waterStandard.setMax_pH_Standard(request.getMax_pH_Standard());
        waterStandard.setMin_pH_Standard(request.getMin_pH_Standard());

        waterStandard.setMinHardnessStandard(request.getMinHardnessStandard());
        waterStandard.setMaxHardnessStandard(request.getMaxHardnessStandard());

        waterStandard.setMaxAmmoniaStandard(request.getMaxAmmoniaStandard());
        waterStandard.setMinAmmoniaStandard(request.getMinAmmoniaStandard());

        waterStandard.setMaxNitriteStandard(request.getMaxNitriteStandard());
        waterStandard.setMinNitriteStandard(request.getMinNitriteStandard());

        waterStandard.setMaxNitrateStandard(request.getMaxNitrateStandard());
        waterStandard.setMinNitrateStandard(request.getMinNitrateStandard());

        waterStandard.setMaxCarbonateStandard(request.getMaxCarbonateStandard());
        waterStandard.setMinCarbonateStandard(request.getMinCarbonateStandard());

        waterStandard.setMaxCarbonDioxideStandard(request.getMaxCarbonDioxideStandard());
        waterStandard.setMinCarbonDioxideStandard(request.getMinCarbonDioxideStandard());

        waterStandard.setMaxSaltStandard(request.getMaxSaltStandard());
        waterStandard.setMinSaltStandard(request.getMinSaltStandard());

        return waterStandardRepository.save(waterStandard);



    }





}
