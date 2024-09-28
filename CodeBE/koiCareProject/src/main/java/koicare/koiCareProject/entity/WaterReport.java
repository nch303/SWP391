package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class WaterReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long waterReportId;

    private Date waterReportUpdatedDate;
    private double waterReportTemperature;
    private double waterReportOxygen;
    private double waterReport_pH;
    private double waterReportHardness;
    private double waterReportAmmonia;
    private double waterReportNitrite;
    private double waterReportNitrate;
    private double waterReportCarbonate;
    private double waterReportSalt;
    private double waterReportCarbonDioxide;

    @ManyToOne
    @JoinColumn(name = "pondid")
    private Pond pond;



}
