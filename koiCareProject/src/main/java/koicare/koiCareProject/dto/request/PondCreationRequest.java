package koicare.koiCareProject.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PondCreationRequest {
    private String pondName;
    private long area;
    private long depth;
    private long volume;
    private long drainCount;
    private long skimmerCount;
    private long pumpingCapacity;
}
