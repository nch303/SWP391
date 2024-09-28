package koicare.koiCareProject.dto.request;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
public class KoiStandardRequest {

    private long period;
    private long koiVarietyID;
}
