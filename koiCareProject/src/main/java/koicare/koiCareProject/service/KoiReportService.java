package koicare.koiCareProject.service;


import koicare.koiCareProject.dto.request.KoiReportRequest;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.repository.KoiReportRepository;
import koicare.koiCareProject.repository.KoiStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KoiReportService {

    @Autowired
    KoiReportRepository koiReportRepository;

    @Autowired
    KoiStatusRepository koiStatusRepository;

    @Autowired
    KoiFishRepository koiFishRepository;


    @Autowired
    ModelMapper modelMapper;

    public KoiReport createKoiReport(KoiReportRequest request){

        KoiReport koiReport = new KoiReport();

        koiReport.setUpdateDate(request.getUpdateDate());
        koiReport.setLength(request.getLength());
        koiReport.setWeight(request.getWeight());
        koiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(request.getKoiStatusID()));
        koiReport.setKoiFish(koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID()));

        return koiReportRepository.save(koiReport);
    }
}
