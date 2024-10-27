package koicare.koiCareProject.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class KoiStandardByVarietyRequest {
    private long varietyID;
    private Date koiBirthday;
    private String koiSex;
}
