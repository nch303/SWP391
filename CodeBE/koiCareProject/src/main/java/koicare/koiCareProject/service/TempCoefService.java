package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.TempCoefRequest;
import koicare.koiCareProject.entity.TempCoef;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.TempCoefRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempCoefService {

    @Autowired
    private TempCoefRepository tempCoefRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TempCoef createTempCoef(TempCoefRequest request) {
        TempCoef tempCoef = modelMapper.map(request, TempCoef.class);
        return tempCoefRepository.save(tempCoef);
    }

    public List<TempCoef> getAllTempCoef() {
        List<TempCoef> tempCoefs = tempCoefRepository.findAll();
        return tempCoefs;
    }

    public TempCoef getTempCoefByTempID(long tempID) {
        TempCoef tempCoef = tempCoefRepository.getTempCoefByTempID(tempID);
        if (tempCoef != null) {
            return tempCoef;
        } else throw new AppException(ErrorCode.TEMPCOEF_IS_NOT_EXISTED);
    }

    public TempCoef updateTempCoef(long tempID, TempCoefRequest request) {
        TempCoef tempCoef = tempCoefRepository.getTempCoefByTempID(tempID);
        if (tempCoef != null) {
            tempCoef = modelMapper.map(request, TempCoef.class);
            tempCoef.setTempID(tempID);
            return tempCoefRepository.save(tempCoef);
        } else throw new AppException(ErrorCode.TEMPCOEF_IS_NOT_EXISTED);

    }

    public void deleteTempCoef(long tempID){
        TempCoef tempCoef = tempCoefRepository.getTempCoefByTempID(tempID);
        if (tempCoef != null) {
            tempCoefRepository.delete(tempCoef);
        } else throw new AppException(ErrorCode.TEMPCOEF_IS_NOT_EXISTED);
    }
}
