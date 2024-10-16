package koicare.koiCareProject.dto.response;

import lombok.Data;

import java.sql.Date;

@Data
public class WaterReportResponse {

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
    private Long pondID;
}
