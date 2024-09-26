package koicare.koiCareProject.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class WaterReportRequest {

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
    private long pondID;
}
