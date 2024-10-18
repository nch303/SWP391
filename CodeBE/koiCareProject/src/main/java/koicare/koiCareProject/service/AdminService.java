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
    private PondStandardRepository pondStandardRepository;

    @Autowired
    private PostDetailRepository postDetailRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private WaterStandardRepository waterStandardRepository;

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

            Shop shop = shopRepository.getShopByAccount(account);
            shop.setNumberOfPosts(shop.getNumberOfPosts() + 1);
            shop.setShopID(shop.getShopID());

            shopRepository.save(shop);
        }
    }


    public WaterStandard createWaterStandard(WaterStandardRequest request){

        List<WaterStandard> waterStandards = waterStandardRepository.findAll();
        if (waterStandards.isEmpty()){
            WaterStandard waterStandard = new WaterStandard();

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

            return waterStandardRepository.save(waterStandard);
        }
        else {
            throw new AppException(ErrorCode.WATER_STANDARD_IS_EXISTED);
        }


    }

    public WaterStandard updateWaterStandard(long waterStandardID, WaterStandardRequest request) {
        WaterStandard waterStandard = waterStandardRepository.findByWaterStandardId(waterStandardID);
        if (waterStandard != null) {
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

            return waterStandardRepository.save(waterStandard);
        } else {
            throw new AppException(ErrorCode.WATER_STANDARD_NOT_EXISTED);
        }
    }

    public WaterStandard getWaterStandardByID(long waterStandardID) {
        WaterStandard waterStandard = waterStandardRepository.findByWaterStandardId(waterStandardID);
        if (waterStandard == null) {
            throw new AppException(ErrorCode.WATER_STANDARD_NOT_EXISTED);
        }
        else{
            return waterStandard;
        }
    }

    public List<WaterStandard> getWaterStandardList() {
        List<WaterStandard> list = waterStandardRepository.findAll();
        if (list.size() <= 0) {
            throw new AppException(ErrorCode.WATER_STANDARD_NOT_EXISTED);
        } else {
            return list;
        }
    }

    public void deleteWaterStandard(long waterStandardID) {
        WaterStandard waterStandard = waterStandardRepository.findByWaterStandardId(waterStandardID);
        if (waterStandard != null) {
            waterStandardRepository.delete(waterStandard);
        } else {
            throw new AppException(ErrorCode.WATER_STANDARD_NOT_EXISTED);
        }
    }



    public PondStandard createPondStandard(PondStandardRequest request) {
        PondStandard pondStandard = new PondStandard();

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

        return pondStandardRepository.save(pondStandard);
    }


    public PondStandard updatePondStandardByID(long pondStandardID, PondStandardRequest request){
        PondStandard pondStandard = pondStandardRepository.findByPondStandardID(pondStandardID);
        if (pondStandard == null) {
            throw new AppException(ErrorCode.POND_STANDARD_NOT_EXISTED);
        }
        else{
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

            return pondStandardRepository.save(pondStandard);
        }

    }





    public PondStandard getPondStandardByID(long pondStandardID) {
        PondStandard pondStandard = pondStandardRepository.findByPondStandardID(pondStandardID);
        if (pondStandard == null) {
            throw new AppException(ErrorCode.POND_STANDARD_NOT_EXISTED);
        } else {
            return pondStandard;
        }
    }



    public void deletePondStandard(long pondStandardID) {
        PondStandard pondStandard = pondStandardRepository.findByPondStandardID(pondStandardID);
        if (pondStandard == null) {
            throw new AppException(ErrorCode.POND_STANDARD_NOT_EXISTED);
        } else{
            pondStandardRepository.delete(pondStandard);
        }
    }

    public List<PondStandard> getAllPondStandard() {
        return pondStandardRepository.findAll();
    }



}
