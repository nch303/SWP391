package koicare.koiCareProject.dto.request;

import lombok.Data;

@Data
public class SaltRequest {

    private long pondID;
    private double currentSalt;
    private double expectSalt;
    private double waterchangePer = 0;
}
