package koicare.koiCareProject.dto.request;

import lombok.Data;

@Data
public class TempCoefRequest {

    private int tempFrom;
    private int tempTo;
    private double coef;
}
