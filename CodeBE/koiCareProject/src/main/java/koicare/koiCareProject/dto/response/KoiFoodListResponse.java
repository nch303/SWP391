package koicare.koiCareProject.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class KoiFoodListResponse {
    long koiFishID;
    String koiName;
    String koiVariety;
    Date birthday;
    double length;
    double weight;
    double food;
}
