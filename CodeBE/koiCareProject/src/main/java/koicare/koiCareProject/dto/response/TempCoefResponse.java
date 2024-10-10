package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class TempCoefResponse {

    private long tempID;

    private int tempFrom;
    private int tempTo;
    private double coef;
}
