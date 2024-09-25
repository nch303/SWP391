package koicare.koiCareProject.dto.request;

import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiStatus;
import lombok.Data;

import java.util.Date;

@Data
public class KoiReportRequest {

    private Date updateDate;
    private double length;
    private double weight;
    private long koiFishID;
    private long koiStatusID;
}
