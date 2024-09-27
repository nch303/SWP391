package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.WaterReportRequest;
import koicare.koiCareProject.entity.WaterReport;
import koicare.koiCareProject.repository.PondRepository;
import koicare.koiCareProject.repository.WaterReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WaterReportService {

    @Autowired
    private WaterReportRepository waterReportRepository;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    ModelMapper modelMapper;

    public WaterReport createWaterReport(WaterReportRequest waterReportRequest) {

        WaterReport waterReport = new WaterReport();

        waterReport.setWaterReportUpdatedDate(new Date());
        waterReport.setWaterReportTemperature(waterReportRequest.getWaterReportTemperature());
        waterReport.setWaterReportSalt(waterReportRequest.getWaterReportSalt());
        waterReport.setWaterReportOxygen(waterReportRequest.getWaterReportOxygen());
        waterReport.setWaterReportNitrite(waterReportRequest.getWaterReportNitrite());
        waterReport.setWaterReportNitrate(waterReportRequest.getWaterReportNitrate());
        waterReport.setWaterReportHardness(waterReportRequest.getWaterReportHardness());
        waterReport.setWaterReportCarbonDioxide(waterReportRequest.getWaterReportCarbonDioxide());
        waterReport.setWaterReportCarbonate(waterReportRequest.getWaterReportCarbonate());
        waterReport.setWaterReportAmmonia(waterReportRequest.getWaterReportAmmonia());
        waterReport.setWaterReport_pH(waterReportRequest.getWaterReport_pH());

        waterReport.setPond(pondRepository.getPondByPondID(waterReportRequest.getPondID()));


        return waterReportRepository.save(waterReport);
    }
}
