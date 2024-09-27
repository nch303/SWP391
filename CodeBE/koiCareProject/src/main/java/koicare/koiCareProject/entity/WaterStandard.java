package koicare.koiCareProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class WaterStandard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long waterStandardId;

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
