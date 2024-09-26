package koicare.koiCareProject.dto.response;

import koicare.koiCareProject.entity.KoiVariety;
import lombok.Data;

@Data
public class KoiStandardResponse {

    private long koiStandID;
    private long period;
    private double lengthMale;
    private double lengthFemale;
    private double weightMale;
    private double weightFemale;
    private double foodMale;
    private double foodFemale;
    private long koiVarietyID;

}
