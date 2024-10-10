package koicare.koiCareProject.dto.request;

import lombok.Data;

@Data
public class SaltRequest2 {
    private long pondID;
    private double currentSalt;
    private double expectSalt;
}
