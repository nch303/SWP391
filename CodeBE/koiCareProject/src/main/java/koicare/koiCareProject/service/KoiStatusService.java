package koicare.koiCareProject.service;


import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.entity.KoiStatus;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.KoiStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoiStatusService {

    @Autowired
    private KoiStatusRepository koiStatusRepository;

    //lấy tất cả các koiStatus
    public List<KoiStatus> getKoiStatuses() {
        return koiStatusRepository.findAll();
    }

    //lấy koiStatus bằng ID
    public KoiStatus getKoiStatus(long koiStatusID) {
        KoiStatus koiStatus = koiStatusRepository.getKoiStatusByKoiStatusID(koiStatusID);

        if (koiStatus != null)
            return koiStatus;
        else
            throw new AppException(ErrorCode.KOISTATUS_NOT_EXISTED);
    }
}
