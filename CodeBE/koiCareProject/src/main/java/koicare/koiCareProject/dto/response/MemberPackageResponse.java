package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class MemberPackageResponse {

    private long packageID;
    private int duration;
    private double price;
}
