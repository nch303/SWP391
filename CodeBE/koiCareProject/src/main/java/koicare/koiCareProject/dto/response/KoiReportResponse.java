package koicare.koiCareProject.dto.response;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiStatus;
import lombok.Data;

import java.util.Date;

@Data
public class KoiReportResponse {

    private long koiReportID;
    private Date updateDate;
    private double length;
    private double weight;
    private long koiFishID;
    private long koiStatusID;
}
