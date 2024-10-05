package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class WaterStandardResponse {

    private double minTempStandard;
    private double maxTempStandard;

    private double minOxygenStandard;
    private double maxOxygenStandard;

    private double min_pH_Standard;
    private double max_pH_Standard;


    private double minHardnessStandard;
    private double maxHardnessStandard;

    private double minAmmoniaStandard;
    private double maxAmmoniaStandard;


    private double minNitriteStandard;
    private double maxNitriteStandard;


    private double minNitrateStandard;
    private double maxNitrateStandard;

    private double minCarbonateStandard;
    private double maxCarbonateStandard;


    private double minSaltStandard;
    private double maxSaltStandard;


    private double minCarbonDioxideStandard;
    private double maxCarbonDioxideStandard;

}
