package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class FeedCoefResponse {
    private long feedCoefID;
    private int ageFrom;
    private int ageTo;
    private double low;
    private double medium;
    private double high;
}
