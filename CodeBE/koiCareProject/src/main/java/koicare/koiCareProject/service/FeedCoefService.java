package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.FeedCoefRequest;
import koicare.koiCareProject.dto.request.TempCoefRequest;
import koicare.koiCareProject.entity.FeedCoef;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.FeedCoefRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedCoefService {

    @Autowired
    FeedCoefRepository feedCoefRepository;

    @Autowired
    ModelMapper modelMapper;

    public FeedCoef createFeedCoef(FeedCoefRequest request) {
        FeedCoef feedCoef = modelMapper.map(request, FeedCoef.class);
        return feedCoefRepository.save(feedCoef);
    }

    public List<FeedCoef> getAllFeedCoef() {
        List<FeedCoef> feedCoefs = feedCoefRepository.findAll();
        return feedCoefs;
    }

    public FeedCoef getFeedCoefByFeedCoefID(long feedCoefID) {
        FeedCoef feedCoef = feedCoefRepository.getFeedCoefByFeedCoefID(feedCoefID);
        if (feedCoef != null) {
            return feedCoef;
        }
        throw new AppException(ErrorCode.FEEDCOEF_IS_NOT_EXISTED);
    }

    public FeedCoef updateFeedCoef(long feedCoefID, FeedCoefRequest request) {
        FeedCoef feedCoef = feedCoefRepository.getFeedCoefByFeedCoefID(feedCoefID);
        if (feedCoef != null) {
            feedCoef = modelMapper.map(request, FeedCoef.class);
            feedCoef.setFeedCoefID(feedCoefID);
            return feedCoefRepository.save(feedCoef);
        }
        throw new AppException(ErrorCode.FEEDCOEF_IS_NOT_EXISTED);
    }

    public void deleteFeedCoef(long feedCoefID) {
        FeedCoef feedCoef = feedCoefRepository.getFeedCoefByFeedCoefID(feedCoefID);
        if (feedCoef != null) {
            feedCoefRepository.delete(feedCoef);
        }else throw new AppException(ErrorCode.FEEDCOEF_IS_NOT_EXISTED);
    }
}
