package koicare.koiCareProject.dto.request;

import koicare.koiCareProject.entity.Member;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PondCreationRequest {
    private String pondName;
    //    private int area;
//    private int depth;
//    private int volume;
//    private int drainCount;
//    private int skimmerCount;
//    private int pumpingCapacity;
    private long memberID;

}
