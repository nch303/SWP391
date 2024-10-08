package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.PostDetailRequest;
import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostDetailService {

    @Autowired
    private PostDetailRepository postDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostPriceRepository postPriceRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    AuthenticationService authenticationService;
    //

    public PostDetail createPostDetail(PostDetailRequest postDetailRequest) {

        PostDetail postDetail = new PostDetail();

        postDetail.setProductName(postDetailRequest.getProductName());
        postDetail.setDescription(postDetailRequest.getDescription());
        Date date = new Date();
        postDetail.setPostDate(date);
        postDetail.setPostStatus(false);
        postDetail.setImage(postDetailRequest.getImage());
        postDetail.setLink(postDetailRequest.getLink());
        postDetail.setProductPrice(postDetailRequest.getProductPrice());


        PostPrice postPrice = postPriceRepository.findByPriceID(postDetailRequest.getPriceID());
        if (postPrice == null) {
            throw new AppException(ErrorCode.POST_PRICE_NOT_EXISTED);
        } else{
            postDetail.setPostPrice(postPrice);
        }

        ProductType productType = productTypeRepository.findByProductTypeID(postDetailRequest.getProducTypeID());
        if (productType == null) {
            throw new AppException(ErrorCode.PRODUCT_TYPE_IS_NOT_EXISTED);
        }
        else{
            postDetail.setProductType(productType);
        }
        Payment payment = paymentRepository.findByPaymentID(postDetailRequest.getPaymentID());
        if (payment == null) {
            throw new AppException(ErrorCode.PAYMENT_METHOD_NOT_FOUND);
        }
        else{
            postDetail.setPayment(payment);
        }


        //postDetail.setShop(shopRepository.findByShopID(postDetailRequest.getShopID()));
        Account account = authenticationService.getCurrentAccount();
        Shop shop = shopRepository.getShopByAccount(account);
        postDetail.setShop(shop);

        return postDetailRepository.save(postDetail);

    }

    public List<PostDetail> getAllPostDetails() {
        return postDetailRepository.findAll();
    }

    public List<PostDetail> getAllPostByShopID(long shopID){
        List<PostDetail> postDetails = postDetailRepository.findAll();
        List<PostDetail> postDetailList = new ArrayList<>();
        for (PostDetail postDetail : postDetails) {
            if (postDetail.getShop().getShopID() == shopID && postDetail.isPostStatus()) {
                postDetailList.add(postDetail);
            }
        }
        if (postDetailList.size() == 0) {
            throw new AppException(ErrorCode.POST_DOES_NOT_EXIST);
        } else{
            return postDetailList;
        }
    }
}
