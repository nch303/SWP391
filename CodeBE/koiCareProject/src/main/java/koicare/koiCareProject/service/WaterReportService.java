package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.WaterReportRequest;
import koicare.koiCareProject.entity.WaterReport;
import koicare.koiCareProject.repository.PondRepository;
import koicare.koiCareProject.repository.WaterReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterReportService {

    @Autowired
    private WaterReportRepository waterReportRepository;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    ModelMapper modelMapper;

    public WaterReport createWaterReport(WaterReportRequest waterReportRequest) {

        WaterReport waterReport = modelMapper.map(waterReportRequest, WaterReport.class);

        waterReport.setPond(pondRepository.getPondByPondID(waterReportRequest.getPondID()));

        return waterReportRepository.save(waterReport);
    }
}
