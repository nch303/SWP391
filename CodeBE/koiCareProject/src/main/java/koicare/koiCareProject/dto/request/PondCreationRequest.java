package koicare.koiCareProject.dto.request;

import koicare.koiCareProject.entity.Member;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PondCreationRequest {

    private String pondName;
    private String pondImage;
    private long area;
    private double depth;
    private long volume;
    private long drainCount;
    private long skimmerCount;
    private long amountFish;
    private long pumpingCapacity;
//    private Long memberID;

}
