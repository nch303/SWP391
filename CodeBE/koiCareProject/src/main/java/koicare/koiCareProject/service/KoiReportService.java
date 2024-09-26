package koicare.koiCareProject.service;


import koicare.koiCareProject.dto.request.KoiReportRequest;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.repository.KoiReportRepository;
import koicare.koiCareProject.repository.KoiStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //tạo KoiReport
    public KoiReport createKoiReport(KoiReportRequest request) {

        KoiReport koiReport = new KoiReport();

        koiReport.setUpdateDate(request.getUpdateDate());
        koiReport.setLength(request.getLength());
        koiReport.setWeight(request.getWeight());
        koiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(request.getKoiStatusID()));
        koiReport.setKoiFish(koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID()));

        return koiReportRepository.save(koiReport);
    }

    //lấy danh sách KoiReport
    public List<KoiReport> getKoiReports() {
        return koiReportRepository.findAll();
    }

    //lấy KoiReport theo ID
    public KoiReport getKoiReport(long koiReportID) {
        KoiReport koiReport = koiReportRepository.getKoiReportByKoiReportID(koiReportID);
        if (koiReport != null)
            return koiReport;
        else
            throw new AppException(ErrorCode.KOIREPORT_NOT_EXISTED);
    }

    //Xóa KoiReport khỏi danh sách
    public void deleteKoiReport(long koiReportID) {
        KoiReport koiReport = koiReportRepository.getKoiReportByKoiReportID(koiReportID);
        if (koiReport != null)
            koiReportRepository.deleteById(koiReportID);
        else
            throw new AppException(ErrorCode.KOIREPORT_NOT_EXISTED);
    }
}
