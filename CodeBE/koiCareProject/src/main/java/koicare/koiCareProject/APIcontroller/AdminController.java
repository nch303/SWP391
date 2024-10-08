package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import koicare.koiCareProject.dto.request.MemberPackageRequest;
import koicare.koiCareProject.dto.request.PondStandardRequest;
import koicare.koiCareProject.dto.request.PostPriceRequest;
import koicare.koiCareProject.dto.request.WaterStandardRequest;
import koicare.koiCareProject.dto.response.*;

import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.service.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin")
@SecurityRequirement(name = "api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PostPriceService postPriceService;

    @Autowired
    private MemberPackageService memberPackageService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PaymentService paymentService;
    //POST DETAIL CONTROLLER
    @GetMapping("post/view/pending")
    public ResponseEntity getPendingPosts() {
        List<PostDetail> postDetails = adminService.getAllPendingPostDetails();
        List<PostDetailResponse> postDetailResponses = new ArrayList<>();
        for (PostDetail postDetail : postDetails) {
            PostDetailResponse postDetailResponse = new PostDetailResponse();

            postDetailResponse.setPostDetailId(postDetail.getPostID());
            postDetailResponse.setProductPrice(postDetail.getProductPrice());
            postDetailResponse.setProductName(postDetail.getProductName());
            postDetailResponse.setPostDate(postDetail.getPostDate());
            postDetailResponse.setImage(postDetail.getImage());
            postDetailResponse.setDescription(postDetail.getDescription());
            postDetailResponse.setLink(postDetail.getLink());
            postDetailResponse.setPostStatus(postDetail.isPostStatus());
            postDetailResponse.setShopID(postDetail.getShop().getShopID());
            postDetailResponse.setProducTypeID(postDetail.getProductType().getProductTypeID());
            postDetailResponse.setPaymentID(postDetail.getPayment().getPaymentID());
            postDetailResponse.setPriceID(postDetail.getPostPrice().getPriceID());

            postDetailResponses.add(postDetailResponse);
        }
        return ResponseEntity.ok(postDetailResponses);
    }

    @PutMapping("post/approve/{postID}")
    public ResponseEntity approvePost(@PathVariable long postID) {
        APIResponse response = new APIResponse();

        adminService.approvedPostDetail(postID);

        response.setResult("POST HAS BEEN APPROVED");
        return ResponseEntity.ok(response);
    }

    @GetMapping("post/view/approved")
    public ResponseEntity getApprovedPosts() {
        List<PostDetail> postDetails = adminService.getAllApprovedPostDetails();
        List<PostDetailResponse> postDetailResponses = new ArrayList<>();
        for (PostDetail postDetail : postDetails) {
            PostDetailResponse postDetailResponse = new PostDetailResponse();

            postDetailResponse.setPostDetailId(postDetail.getPostID());
            postDetailResponse.setProductPrice(postDetail.getProductPrice());
            postDetailResponse.setProductName(postDetail.getProductName());
            postDetailResponse.setPostDate(postDetail.getPostDate());
            postDetailResponse.setImage(postDetail.getImage());
            postDetailResponse.setDescription(postDetail.getDescription());
            postDetailResponse.setLink(postDetail.getLink());
            postDetailResponse.setPostStatus(postDetail.isPostStatus());
            postDetailResponse.setShopID(postDetail.getShop().getShopID());
            postDetailResponse.setProducTypeID(postDetail.getProductType().getProductTypeID());
            postDetailResponse.setPaymentID(postDetail.getPayment().getPaymentID());
            postDetailResponse.setPriceID(postDetail.getPostPrice().getPriceID());

            postDetailResponses.add(postDetailResponse);
        }
        return ResponseEntity.ok(postDetailResponses);
    }


    //Standard Controller
    @PutMapping("waterstandard/update")
    public ResponseEntity updateWaterStandard(@RequestBody WaterStandardRequest request) {
        APIResponse<WaterStandardResponse> response = new APIResponse<>();


        WaterStandardResponse waterStandardResponse = new WaterStandardResponse();
        adminService.updateWaterStandard(request);

        waterStandardResponse.setMax_pH_Standard(request.getMax_pH_Standard());
        waterStandardResponse.setMin_pH_Standard(request.getMin_pH_Standard());

        waterStandardResponse.setMaxTempStandard(request.getMaxTempStandard());
        waterStandardResponse.setMinTempStandard(request.getMinTempStandard());

        waterStandardResponse.setMinOxygenStandard(request.getMinOxygenStandard());
        waterStandardResponse.setMaxOxygenStandard(request.getMaxOxygenStandard());

        waterStandardResponse.setMaxHardnessStandard(request.getMaxHardnessStandard());
        waterStandardResponse.setMinHardnessStandard(request.getMinHardnessStandard());

        waterStandardResponse.setMinAmmoniaStandard(request.getMinAmmoniaStandard());
        waterStandardResponse.setMaxAmmoniaStandard(request.getMaxAmmoniaStandard());

        waterStandardResponse.setMinNitriteStandard(request.getMinNitriteStandard());
        waterStandardResponse.setMaxNitriteStandard(request.getMaxNitriteStandard());

        waterStandardResponse.setMinNitrateStandard(request.getMinNitrateStandard());
        waterStandardResponse.setMaxNitrateStandard(request.getMaxNitrateStandard());

        waterStandardResponse.setMaxCarbonateStandard(request.getMaxCarbonateStandard());
        waterStandardResponse.setMinCarbonateStandard(request.getMinCarbonateStandard());

        waterStandardResponse.setMaxCarbonDioxideStandard(request.getMaxCarbonDioxideStandard());
        waterStandardResponse.setMinCarbonDioxideStandard(request.getMinCarbonDioxideStandard());

        waterStandardResponse.setMaxSaltStandard(request.getMaxSaltStandard());
        waterStandardResponse.setMinSaltStandard(request.getMinSaltStandard());

        response.setResult(waterStandardResponse);
        return ResponseEntity.ok(response);
    }


    @PutMapping("pondstandard/update")
    public ResponseEntity updatePondStandard(@RequestBody PondStandardRequest request) {
        APIResponse<PondStandardResponse> response = new APIResponse<>();

        PondStandardResponse pondStandardResponse = new PondStandardResponse();
        adminService.updatePondStandard(request);

        pondStandardResponse.setMaxDepth(request.getMaxDepth());
        pondStandardResponse.setMinDepth(request.getMinDepth());

        pondStandardResponse.setMaxArea(request.getMaxArea());
        pondStandardResponse.setMinArea(request.getMinArea());

        pondStandardResponse.setMaxAmountFish(request.getMaxAmountFish());
        pondStandardResponse.setMinAmountFish(request.getMinAmountFish());

        pondStandardResponse.setMaxSkimmerCount(request.getMaxSkimmerCount());
        pondStandardResponse.setMinSkimmerCount(request.getMinSkimmerCount());

        pondStandardResponse.setMaxVolume(request.getMaxVolume());
        pondStandardResponse.setMinVolume(request.getMinVolume());

        pondStandardResponse.setMinDrainCount(request.getMinDrainCount());
        pondStandardResponse.setMaxDrainCount(request.getMaxDrainCount());

        pondStandardResponse.setMaxPumpingCapacity(request.getMaxPumpingCapacity());
        pondStandardResponse.setMinPumpingCapacity(request.getMinPumpingCapacity());

        response.setResult(pondStandardResponse);

        return ResponseEntity.ok(response);
    }

    //POST PRICE CONTROLLER
    @PostMapping("postprice/create")
    public ResponseEntity createPostPrice(@RequestBody PostPriceRequest request) {
        APIResponse<PostPriceResponse> response = new APIResponse<>();
        PostPrice postPrice = postPriceService.createPostPrice(request);
        PostPriceResponse postPriceResponse = new PostPriceResponse();
        postPriceResponse.setPrice(postPrice.getPrice());
        postPriceResponse.setDuration(postPrice.getDuration());
        postPriceResponse.setPriceID(postPrice.getPriceID());
        response.setResult(postPriceResponse);
        return ResponseEntity.ok(response);

    }

    @GetMapping("postprice/view")
    public ResponseEntity getPostPriceViews() {
        List<PostPrice> postPriceList = postPriceService.getAllPostPrice();
        List<PostPriceResponse> postPriceResponseList = new ArrayList<>();
        for (PostPrice postPrice : postPriceList) {
            PostPriceResponse postPriceResponse1 = new PostPriceResponse();
            postPriceResponse1.setPrice(postPrice.getPrice());
            postPriceResponse1.setDuration(postPrice.getDuration());
            postPriceResponse1.setPriceID(postPrice.getPriceID());
            postPriceResponseList.add(postPriceResponse1);
        }
        return ResponseEntity.ok(postPriceList);
    }

    @PutMapping("postprice/update/{priceID}")
    public ResponseEntity updatePostPrice(@PathVariable("priceID") long priceID, @RequestBody PostPriceRequest request) {
        PostPriceResponse postPriceResponse = new PostPriceResponse();
        postPriceService.updatePostPrice(priceID, request);
        postPriceResponse.setPriceID(priceID);
        postPriceResponse.setPrice(request.getPrice());
        postPriceResponse.setDuration(request.getDuration());
        return ResponseEntity.ok(postPriceResponse);
    }

    @DeleteMapping("postprice/delete/{priceID}")
    public ResponseEntity deletePostPrice(@PathVariable("priceID") long priceID) {
        APIResponse apiResponse = new APIResponse();
        postPriceService.deletePostPrice(priceID);
        apiResponse.setResult("DELETED SUCCESSFULLY");
        return ResponseEntity.ok(apiResponse);

    }

    //MEMBER PACKAGE CONTROLLER

    @PostMapping("memberpackage/create")
    public ResponseEntity createMemberPackage(@RequestBody MemberPackageRequest request) {
        APIResponse<MemberPackageResponse> response = new APIResponse<>();

        MemberPackageResponse memberPackageResponse = new MemberPackageResponse();
        MemberPackage memberPackage = memberPackageService.createMemberPackage(request);
        memberPackageResponse.setPackageID(memberPackage.getPackageID());
        memberPackageResponse.setDuration(memberPackage.getDuration());
        memberPackageResponse.setPrice(memberPackage.getPrice());
        response.setResult(memberPackageResponse);
        return ResponseEntity.ok(response);

    }

    @GetMapping("memberpackage/view")
    public ResponseEntity getMemberPackageViews() {
        List<MemberPackage> memberPackageList = memberPackageService.getAllMemberPackages();
        List<MemberPackageResponse> memberPackageResponseList = new ArrayList<>();
        for (MemberPackage memberPackage : memberPackageList) {
            MemberPackageResponse memberPackageResponse1 = new MemberPackageResponse();
            memberPackageResponse1.setPackageID(memberPackage.getPackageID());
            memberPackageResponse1.setDuration(memberPackage.getDuration());
            memberPackageResponse1.setPrice(memberPackage.getPrice());
            memberPackageResponseList.add(memberPackageResponse1);

        }
        return ResponseEntity.ok(memberPackageResponseList);
    }

    @PutMapping("memberpackage/update/{packageID}")
    public ResponseEntity updateMemberPackage(@PathVariable("packageID") long packageID, @RequestBody MemberPackageRequest request) {
        APIResponse<MemberPackageResponse> response = new APIResponse<>();
        MemberPackage memberPackage = memberPackageService.updateMemberPackage(packageID, request);
        MemberPackageResponse memberPackageResponse = new MemberPackageResponse();
        memberPackageResponse.setPackageID(memberPackage.getPackageID());
        memberPackageResponse.setDuration(memberPackage.getDuration());
        memberPackageResponse.setPrice(memberPackage.getPrice());
        response.setResult(memberPackageResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("memberpackage/delete/{packageID}")
    public ResponseEntity deleteMemberPackage(@PathVariable("packageID") long packageID) {
        APIResponse apiResponse = new APIResponse();
        memberPackageService.deleteMemberPackage(packageID);
        apiResponse.setResult("DELETED SUCCESSFULLY");
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("deleteAccount/{accountID}")
    public ResponseEntity deleteAccount(@PathVariable("accountID") long accountID){
        Account account = authenticationService.deleteAccount(accountID);
        return ResponseEntity.ok("Deleted account id: " + accountID + " successfully");
    }

    @PutMapping("restoreAccount/{accountID}")
    public ResponseEntity unbanAccount(@PathVariable("accountID") long accountID){
        Account account = authenticationService.restoreAccount(accountID);
        return ResponseEntity.ok("Restore account id: " + accountID + " successfully");
    }


    //Payment Controller

    @GetMapping("/payment/viewall")
    public ResponseEntity getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayment());
    }

}
