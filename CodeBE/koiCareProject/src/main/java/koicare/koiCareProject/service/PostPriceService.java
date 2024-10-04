package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.PostPriceRequest;
import koicare.koiCareProject.entity.PostPrice;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.PostPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PostPriceService {

    @Autowired
    private PostPriceRepository postPriceRepository;

    public PostPrice createPostPrice(@RequestBody PostPriceRequest request){
        PostPrice postPrice = new PostPrice();

        postPrice.setPrice(request.getPrice());
        postPrice.setDuration(request.getDuration());
        return postPriceRepository.save(postPrice);
    }

    public List<PostPrice> getAllPostPrice(){
        return postPriceRepository.findAll();
    }

    public PostPrice updatePostPrice(long priceID, PostPriceRequest request){
        PostPrice postPrice = postPriceRepository.findByPriceID(priceID);
        if (postPrice != null){
            postPrice.setPrice(request.getPrice());
            postPrice.setDuration(request.getDuration());
            return postPriceRepository.save(postPrice);
        }
        else throw new AppException(ErrorCode.POST_PRICE_NOT_EXISTED);
    }

    public void deletePostPrice(long priceID){
        PostPrice postPrice = postPriceRepository.findByPriceID(priceID);
        if (postPrice != null){
            postPriceRepository.delete(postPrice);
        } else throw new AppException(ErrorCode.POST_PRICE_NOT_EXISTED);
    }

}
