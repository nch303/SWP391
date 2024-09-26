package koicare.koiCareProject.dto.request;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class KoiStandardRequest {

    private long period;
    private double lengthMale;
    private double lengthFemale;
    private double weightMale;
    private double weightFemale;
    private double foodMale;
    private double foodFemale;
    private long koiVarietyID;
}
