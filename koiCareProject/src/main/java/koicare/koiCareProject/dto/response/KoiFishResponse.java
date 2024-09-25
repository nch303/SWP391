package koicare.koiCareProject.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class KoiFishResponse {

    private long koiFishID;
    private String koiName;
    private Date birthday;
    private String koiSex;
    private String image;
    private long pondID;
    private long koiVarietyID;

}
