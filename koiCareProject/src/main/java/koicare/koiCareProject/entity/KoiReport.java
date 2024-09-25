package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class KoiReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long koiReportID;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    private double length;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "koifishid", nullable = false)
    private KoiFish koiFish;

    @ManyToOne
    @JoinColumn(name = "koistatusid", nullable = false)
    private KoiStatus koiStatus;
}
