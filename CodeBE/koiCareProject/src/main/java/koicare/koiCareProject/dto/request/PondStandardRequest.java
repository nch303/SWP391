package koicare.koiCareProject.dto.request;

import lombok.Data;

@Data
public class PondStandardRequest {

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
