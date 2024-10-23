package koicare.koiCareProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class PondStandard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
