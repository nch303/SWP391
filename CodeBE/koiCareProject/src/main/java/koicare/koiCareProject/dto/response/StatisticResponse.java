package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class StatisticResponse {
    private long ageByDate;
    private double lowLength;
    private double hiLength;
    private double lowWeight;
    private double hiWeight;
    private double lowLengthto;
    private double hiLengthto;
    private double lowWeightto;
    private double hiWeightto;

}
