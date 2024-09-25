package koicare.koiCareProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KoiFishRequest {

    private String koiName;
    private Date birthday;
    private String koiSex;
    private String image;
    private Long pondID;
    private Long koiVarietyID;
}
