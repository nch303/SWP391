package koicare.koiCareProject.dto.request;

import lombok.Data;

@Data
public class KoiFoodRequest {

    private int temperature;
    private String level;
    private long pondID;
}
