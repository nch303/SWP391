package koicare.koiCareProject.dto.request;

import lombok.Data;

@Data
public class KoiFoodRequest {

    int temperature;
    String level;
    long pondID;
}
