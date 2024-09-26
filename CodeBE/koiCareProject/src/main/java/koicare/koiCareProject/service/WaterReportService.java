package koicare.koiCareProject.service;

import koicare.koiCareProject.entity.WaterReport;
import koicare.koiCareProject.repository.PondRepository;
import koicare.koiCareProject.repository.WaterReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterReportService {

    @Autowired
    private WaterReportRepository waterReportRepository;

    @Autowired
    private PondRepository pondRepository;

//    public WaterReport createWaterReport() {
//
//
//    }
}
