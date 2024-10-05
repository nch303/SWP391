package koicare.koiCareProject.dto.request;

import lombok.Data;

@Data
public class PondStandardRequest {

    private long minArea;
    private long maxArea;

    private double minDepth;
    private double maxDepth;

    private long minVolume;
    private long maxVolume;

    private long minDrainCount;
    private long maxDrainCount;

    private long minSkimmerCount;
    private long maxSkimmerCount;

    private long minAmountFish;
    private long maxAmountFish;

    private long minPumpingCapacity;
    private long maxPumpingCapacity;
}
