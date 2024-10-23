package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class PondStandardResponse {
    private long pondStandardID;

    private long area;

    private double minDepth;
    private double maxDepth;

    private long minVolume;
    private long maxVolume;

    private long drainCount;

    private long skimmerCount;

    private long minAmountFish;
    private long maxAmountFish;

    private long minPumpingCapacity;
    private long maxPumpingCapacity;
}
