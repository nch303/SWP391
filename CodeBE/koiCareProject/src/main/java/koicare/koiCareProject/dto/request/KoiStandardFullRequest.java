package koicare.koiCareProject.dto.request;

import lombok.Data;

@Data
public class KoiStandardFullRequest {
    private long period;
    private double lowLengthMale;
    private double medLengthMale;
    private double hiLengthMale;
    private double lowLengthFemale;
    private double medLengthFemale;
    private double hiLengthFemale;
    private double lowWeightMale;
    private double medWeightMale;
    private double hiWeightMale;
    private double lowWeightFemale;
    private double medWeightFemale;
    private double hiWeightFemale;
    private long koiVarietyID;
}
