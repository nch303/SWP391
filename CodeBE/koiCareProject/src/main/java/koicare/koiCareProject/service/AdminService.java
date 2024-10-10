package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.EmailDetail;
import koicare.koiCareProject.dto.request.PondStandardRequest;
import koicare.koiCareProject.dto.request.WaterStandardRequest;
import koicare.koiCareProject.dto.response.PostDetailResponse;
import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private PostDetailRepository postDetailRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmailService emailService;

    public List<PostDetail> getAllPendingPostDetails(){

        return postDetailRepository.findByPostStatus(false);
    }

    public List<PostDetail> getAllApprovedPostDetails(){

        return postDetailRepository.findByPostStatus(true);
    }

    public PostDetail approvedPostDetail(Long postID){
        PostDetail postDetail = postDetailRepository.findByPostID(postID);

        if (postDetail == null) {
            throw new AppException(ErrorCode.POST_DOES_NOT_EXIST);
        }
        else{
            postDetail.setPostStatus(true);
            return postDetailRepository.save(postDetail);
        }
    }

    //rejected postdetail
    public void rejectedPostDetail(Long postID){
        PostDetail postDetail = postDetailRepository.findByPostID(postID);

        if (postDetail == null) {
            throw new AppException(ErrorCode.POST_DOES_NOT_EXIST);
        }
        else{
            long shopID = postDetail.getShop().getShopID();
            Account account = accountRepository.findAccountByAccountID(postDetail.getShop().getAccount().getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setAccount(account);
            emailDetail.setSubject("Your post is rejected!");
            emailDetail.setLink("http://103.90.227.68/shop/postManager");

            emailService.sendEmailRejectPost(emailDetail);
            postDetailRepository.delete(postDetail);
        }
    }

    @Autowired
    private WaterStandardRepository waterStandardRepository;


    public void updateWaterStandard(WaterStandardRequest request) {


        List<WaterStandard> list = waterStandardRepository.findAll();
        if (list.size() <= 0) {
            WaterStandard waterStandard = new WaterStandard();

            waterStandard.setMinTempStandard(0);
            waterStandard.setMaxTempStandard(0);

            waterStandard.setMaxOxygenStandard(0);
            waterStandard.setMinOxygenStandard(0);

            waterStandard.setMax_pH_Standard(0);
            waterStandard.setMin_pH_Standard(0);

            waterStandard.setMinHardnessStandard(0);
            waterStandard.setMaxHardnessStandard(0);

            waterStandard.setMaxAmmoniaStandard(0);
            waterStandard.setMinAmmoniaStandard(0);

            waterStandard.setMaxNitriteStandard(0);
            waterStandard.setMinNitriteStandard(0);

            waterStandard.setMaxNitrateStandard(0);
            waterStandard.setMinNitrateStandard(0);

            waterStandard.setMaxCarbonateStandard(0);
            waterStandard.setMinCarbonateStandard(0);

            waterStandard.setMaxCarbonDioxideStandard(0);
            waterStandard.setMinCarbonDioxideStandard(0);

            waterStandard.setMaxSaltStandard(0);
            waterStandard.setMinSaltStandard(0);

            waterStandardRepository.save(waterStandard);

        }

        WaterStandard waterStandard = waterStandardRepository.findByWaterStandardId(1);

        waterStandard.setMinTempStandard(request.getMinTempStandard());
        waterStandard.setMaxTempStandard(request.getMaxTempStandard());

        waterStandard.setMaxOxygenStandard(request.getMaxOxygenStandard());
        waterStandard.setMinOxygenStandard(request.getMinOxygenStandard());

        waterStandard.setMax_pH_Standard(request.getMax_pH_Standard());
        waterStandard.setMin_pH_Standard(request.getMin_pH_Standard());

        waterStandard.setMinHardnessStandard(request.getMinHardnessStandard());
        waterStandard.setMaxHardnessStandard(request.getMaxHardnessStandard());

        waterStandard.setMaxAmmoniaStandard(request.getMaxAmmoniaStandard());
        waterStandard.setMinAmmoniaStandard(request.getMinAmmoniaStandard());

        waterStandard.setMaxNitriteStandard(request.getMaxNitriteStandard());
        waterStandard.setMinNitriteStandard(request.getMinNitriteStandard());

        waterStandard.setMaxNitrateStandard(request.getMaxNitrateStandard());
        waterStandard.setMinNitrateStandard(request.getMinNitrateStandard());

        waterStandard.setMaxCarbonateStandard(request.getMaxCarbonateStandard());
        waterStandard.setMinCarbonateStandard(request.getMinCarbonateStandard());

        waterStandard.setMaxCarbonDioxideStandard(request.getMaxCarbonDioxideStandard());
        waterStandard.setMinCarbonDioxideStandard(request.getMinCarbonDioxideStandard());

        waterStandard.setMaxSaltStandard(request.getMaxSaltStandard());
        waterStandard.setMinSaltStandard(request.getMinSaltStandard());

        waterStandardRepository.save(waterStandard);
    }

    @Autowired
    private PondStandardRepository pondStandardRepository;

    public void updatePondStandard(PondStandardRequest request){
        List<PondStandard> pondStandards = pondStandardRepository.findAll();

        if (pondStandards.size() <= 0){
            PondStandard pondStandard = new PondStandard();

            pondStandard.setMaxArea(0);
            pondStandard.setMinArea(0);

            pondStandard.setMaxVolume(0);
            pondStandard.setMinVolume(0);

            pondStandard.setMaxSkimmerCount(0);
            pondStandard.setMinSkimmerCount(0);

            pondStandard.setMinDepth(0);
            pondStandard.setMaxDepth(0);

            pondStandard.setMaxPumpingCapacity(0);
            pondStandard.setMinPumpingCapacity(0);

            pondStandard.setMinDrainCount(0);
            pondStandard.setMaxDrainCount(0);

            pondStandard.setMinAmountFish(0);
            pondStandard.setMaxAmountFish(0);
            pondStandardRepository.save(pondStandard);
        }

        PondStandard pondStandard = pondStandardRepository.findByPondStandardID(1);

        pondStandard.setMaxArea(request.getMaxArea());
        pondStandard.setMinArea(request.getMinArea());

        pondStandard.setMaxVolume(request.getMaxVolume());
        pondStandard.setMinVolume(request.getMinVolume());

        pondStandard.setMaxSkimmerCount(request.getMaxSkimmerCount());
        pondStandard.setMinSkimmerCount(request.getMinSkimmerCount());

        pondStandard.setMinDepth(request.getMinDepth());
        pondStandard.setMaxDepth(request.getMaxDepth());

        pondStandard.setMaxPumpingCapacity(request.getMaxPumpingCapacity());
        pondStandard.setMinPumpingCapacity(request.getMinPumpingCapacity());

        pondStandard.setMinDrainCount(request.getMinDrainCount());
        pondStandard.setMaxDrainCount(request.getMaxDrainCount());

        pondStandard.setMinAmountFish(request.getMinAmountFish());
        pondStandard.setMaxAmountFish(request.getMaxAmountFish());

        pondStandardRepository.save(pondStandard);
    }

    public ProductType createProductType(ProductType type){
        ProductType productType = new ProductType();
        return productTypeRepository.save(productType);
    }



}
