package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class PondResponse {

    private Long pondID;
    private String pondName;
    private String pondImage;
    private long area;
    private double depth;
    private long volume;
    private long drainCount;
    private long skimmerCount;
    private long amountFish;
    private long pumpingCapacity;
    private Long memberID;
    private double totalWeight;
}
