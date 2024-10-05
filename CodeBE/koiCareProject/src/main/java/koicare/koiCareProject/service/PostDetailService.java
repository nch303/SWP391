package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.PostDetailRequest;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.PostDetail;
import koicare.koiCareProject.entity.Shop;
import koicare.koiCareProject.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public PostDetail createPostDetail(PostDetailRequest postDetailRequest) {

        PostDetail postDetail = new PostDetail();

        postDetail.setProductName(postDetailRequest.getProductName());
        postDetail.setDescription(postDetailRequest.getDescription());
        postDetail.setPostDate(postDetailRequest.getPostDate());
        postDetail.setPostStatus(false);
        postDetail.setImage(postDetailRequest.getImage());
        postDetail.setLink(postDetailRequest.getLink());
        postDetail.setProductPrice(postDetailRequest.getProductPrice());




        postDetail.setPostPrice(postPriceRepository.findByPriceID(postDetailRequest.getPriceID()));

        postDetail.setProductType(productTypeRepository.findByProductTypeID(postDetailRequest.getProducTypeID()));
        postDetail.setPayment(paymentRepository.findByPaymentID(postDetailRequest.getPaymentID()));

        //postDetail.setShop(shopRepository.findByShopID(postDetailRequest.getShopID()));
        Account account = authenticationService.getCurrentAccount();
        Shop shop = shopRepository.getShopByAccount(account);
        postDetail.setShop(shop);

        return postDetailRepository.save(postDetail);

    }

    public List<PostDetail> getAllPostDetails() {
        return postDetailRepository.findAll();
    }
}
