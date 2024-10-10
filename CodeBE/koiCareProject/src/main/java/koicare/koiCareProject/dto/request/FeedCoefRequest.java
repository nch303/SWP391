package koicare.koiCareProject.dto.request;

import lombok.Data;

@Data
public class FeedCoefRequest {
    private int ageFrom;
    private int ageTo;
    private double low;
    private double medium;
    private double high;
}
