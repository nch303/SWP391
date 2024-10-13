package koicare.koiCareProject.dto.response;
import lombok.Data;
import java.util.Date;
@Data
public class KoiFoodListResponse {
    private long koiFishID;
    private String koiName;
    private String koiVariety;
    private Date birthday;
    private double length;
    private double weight;
    private double food;
}