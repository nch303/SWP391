package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import koicare.koiCareProject.dto.request.MemberPackageRequest;
import koicare.koiCareProject.dto.request.PondStandardRequest;
import koicare.koiCareProject.dto.request.PostPriceRequest;
import koicare.koiCareProject.dto.request.WaterStandardRequest;
import koicare.koiCareProject.dto.response.*;

import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.repository.ProductTypeRepository;
import koicare.koiCareProject.service.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin")
@SecurityRequirement(name = "api")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    ProductTypeRepository productTypeRepository;



    //POST DETAIL CONTROLLER ADMIN
    @GetMapping("post/view/pending")
    public ResponseEntity getPendingPosts() {
        APIResponse<List<PostDetailResponse>> response = new APIResponse<>();
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
            postDetailResponse.setProductTypeName(postDetail.getProductType().getProductTypeName());
            postDetailResponse.setExpiredDate(postDetail.getExpiredDate());

            postDetailResponses.add(postDetailResponse);
        }
        response.setResult(postDetailResponses);
        return ResponseEntity.ok(response);
    }

    //approved a post
    @PutMapping("post/approve/{postID}")
    public ResponseEntity approvePost(@PathVariable long postID) {
        APIResponse response = new APIResponse();

        adminService.approvedPostDetail(postID);

        response.setResult("POST HAS BEEN APPROVED");
        return ResponseEntity.ok(response);
    }

    //reject a post
    @DeleteMapping("post/reject/{postID}")
    public ResponseEntity rejectPost(@PathVariable long postID) {
        APIResponse response = new APIResponse();

        adminService.rejectedPostDetail(postID);

        response.setResult("POST HAS BEEN REJECTED");
        return ResponseEntity.ok(response);
    }

    //delete a post
    @DeleteMapping("post/delete/{postID}")
    public ResponseEntity deletePost(@PathVariable long postID) {
        APIResponse response = new APIResponse();

        adminService.deletePostDetail(postID);

        response.setResult("POST HAS BEEN DELETED");
        return ResponseEntity.ok(response);
    }

    //view approved post
    @GetMapping("post/view/approved")
    public ResponseEntity getApprovedPosts() {
        APIResponse<List<PostDetailResponse>> response = new APIResponse<>();
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
            postDetailResponse.setProductTypeName(postDetail.getProductType().getProductTypeName());
            postDetailResponse.setExpiredDate(postDetail.getExpiredDate());

            postDetailResponses.add(postDetailResponse);
        }

        Collections.sort(postDetailResponses, Comparator.comparing(PostDetailResponse::getPostDetailId).reversed());
        response.setResult(postDetailResponses);

        return ResponseEntity.ok(response);
    }


    //Standard Controller
    //create water standard
    @PostMapping("waterstandard/create")
    public ResponseEntity createWaterStandard(WaterStandardRequest request) {
        APIResponse<WaterStandardResponse> response = new APIResponse<>();
        WaterStandardResponse waterStandardResponse = new WaterStandardResponse();
        WaterStandard waterStandard = adminService.createWaterStandard(request);

        waterStandardResponse.setWaterStandardID(waterStandard.getWaterStandardId());

        waterStandardResponse.setMax_pH_Standard(waterStandard.getMax_pH_Standard());
        waterStandardResponse.setMin_pH_Standard(waterStandard.getMin_pH_Standard());

        waterStandardResponse.setMaxTempStandard(waterStandard.getMaxTempStandard());
        waterStandardResponse.setMinTempStandard(waterStandard.getMinTempStandard());

        waterStandardResponse.setMinOxygenStandard(waterStandard.getMinOxygenStandard());
        waterStandardResponse.setMaxOxygenStandard(waterStandard.getMaxOxygenStandard());

        waterStandardResponse.setMaxHardnessStandard(waterStandard.getMaxHardnessStandard());
        waterStandardResponse.setMinHardnessStandard(waterStandard.getMinHardnessStandard());

        waterStandardResponse.setMinAmmoniaStandard(waterStandard.getMinAmmoniaStandard());
        waterStandardResponse.setMaxAmmoniaStandard(waterStandard.getMaxAmmoniaStandard());

        waterStandardResponse.setMinNitriteStandard(waterStandard.getMinNitriteStandard());
        waterStandardResponse.setMaxNitriteStandard(waterStandard.getMaxNitriteStandard());

        waterStandardResponse.setMinNitrateStandard(waterStandard.getMinNitrateStandard());
        waterStandardResponse.setMaxNitrateStandard(waterStandard.getMaxNitrateStandard());

        waterStandardResponse.setMaxCarbonateStandard(waterStandard.getMaxCarbonateStandard());
        waterStandardResponse.setMinCarbonateStandard(waterStandard.getMinCarbonateStandard());

        waterStandardResponse.setMaxCarbonDioxideStandard(waterStandard.getMaxCarbonDioxideStandard());
        waterStandardResponse.setMinCarbonDioxideStandard(waterStandard.getMinCarbonDioxideStandard());

        waterStandardResponse.setMaxSaltStandard(waterStandard.getMaxSaltStandard());
        waterStandardResponse.setMinSaltStandard(waterStandard.getMinSaltStandard());

        response.setResult(waterStandardResponse);
        return ResponseEntity.ok(response);

    }

    //update waterstandard
    @PutMapping("waterstandard/update/{waterStandardID}")
    public ResponseEntity updateWaterStandard(@PathVariable("waterStandardID") long waterStandardId,@RequestBody WaterStandardRequest request) {
        APIResponse<WaterStandardResponse> response = new APIResponse<>();


        WaterStandardResponse waterStandardResponse = new WaterStandardResponse();
        WaterStandard waterStandard = adminService.updateWaterStandard(waterStandardId ,request);

        waterStandardResponse.setWaterStandardID(waterStandard.getWaterStandardId());

        waterStandardResponse.setMax_pH_Standard(waterStandard.getMax_pH_Standard());
        waterStandardResponse.setMin_pH_Standard(waterStandard.getMin_pH_Standard());

        waterStandardResponse.setMaxTempStandard(waterStandard.getMaxTempStandard());
        waterStandardResponse.setMinTempStandard(waterStandard.getMinTempStandard());

        waterStandardResponse.setMinOxygenStandard(waterStandard.getMinOxygenStandard());
        waterStandardResponse.setMaxOxygenStandard(waterStandard.getMaxOxygenStandard());

        waterStandardResponse.setMaxHardnessStandard(waterStandard.getMaxHardnessStandard());
        waterStandardResponse.setMinHardnessStandard(waterStandard.getMinHardnessStandard());

        waterStandardResponse.setMinAmmoniaStandard(waterStandard.getMinAmmoniaStandard());
        waterStandardResponse.setMaxAmmoniaStandard(waterStandard.getMaxAmmoniaStandard());

        waterStandardResponse.setMinNitriteStandard(waterStandard.getMinNitriteStandard());
        waterStandardResponse.setMaxNitriteStandard(waterStandard.getMaxNitriteStandard());

        waterStandardResponse.setMinNitrateStandard(waterStandard.getMinNitrateStandard());
        waterStandardResponse.setMaxNitrateStandard(waterStandard.getMaxNitrateStandard());

        waterStandardResponse.setMaxCarbonateStandard(waterStandard.getMaxCarbonateStandard());
        waterStandardResponse.setMinCarbonateStandard(waterStandard.getMinCarbonateStandard());

        waterStandardResponse.setMaxCarbonDioxideStandard(waterStandard.getMaxCarbonDioxideStandard());
        waterStandardResponse.setMinCarbonDioxideStandard(waterStandard.getMinCarbonDioxideStandard());

        waterStandardResponse.setMaxSaltStandard(waterStandard.getMaxSaltStandard());
        waterStandardResponse.setMinSaltStandard(waterStandard.getMinSaltStandard());

        response.setResult(waterStandardResponse);
        return ResponseEntity.ok(response);
    }

    //view all waterstandard
    @GetMapping("viewall/waterstandard")
    public ResponseEntity viewAllWaterStandard() {
        APIResponse<List<WaterStandardResponse>> response = new APIResponse<>();
        List<WaterStandardResponse> responses = new ArrayList<>();
        List<WaterStandard> waterStandards = adminService.getWaterStandardList();
        for (WaterStandard waterStandard : waterStandards) {
            WaterStandardResponse waterStandardResponse = new WaterStandardResponse();

            waterStandardResponse.setWaterStandardID(waterStandard.getWaterStandardId());

            waterStandardResponse.setMax_pH_Standard(waterStandard.getMax_pH_Standard());
            waterStandardResponse.setMin_pH_Standard(waterStandard.getMin_pH_Standard());

            waterStandardResponse.setMaxTempStandard(waterStandard.getMaxTempStandard());
            waterStandardResponse.setMinTempStandard(waterStandard.getMinTempStandard());

            waterStandardResponse.setMinOxygenStandard(waterStandard.getMinOxygenStandard());
            waterStandardResponse.setMaxOxygenStandard(waterStandard.getMaxOxygenStandard());

            waterStandardResponse.setMaxHardnessStandard(waterStandard.getMaxHardnessStandard());
            waterStandardResponse.setMinHardnessStandard(waterStandard.getMinHardnessStandard());

            waterStandardResponse.setMinAmmoniaStandard(waterStandard.getMinAmmoniaStandard());
            waterStandardResponse.setMaxAmmoniaStandard(waterStandard.getMaxAmmoniaStandard());

            waterStandardResponse.setMinNitriteStandard(waterStandard.getMinNitriteStandard());
            waterStandardResponse.setMaxNitriteStandard(waterStandard.getMaxNitriteStandard());

            waterStandardResponse.setMinNitrateStandard(waterStandard.getMinNitrateStandard());
            waterStandardResponse.setMaxNitrateStandard(waterStandard.getMaxNitrateStandard());

            waterStandardResponse.setMaxCarbonateStandard(waterStandard.getMaxCarbonateStandard());
            waterStandardResponse.setMinCarbonateStandard(waterStandard.getMinCarbonateStandard());

            waterStandardResponse.setMaxCarbonDioxideStandard(waterStandard.getMaxCarbonDioxideStandard());
            waterStandardResponse.setMinCarbonDioxideStandard(waterStandard.getMinCarbonDioxideStandard());

            waterStandardResponse.setMaxSaltStandard(waterStandard.getMaxSaltStandard());
            waterStandardResponse.setMinSaltStandard(waterStandard.getMinSaltStandard());

            responses.add(waterStandardResponse);
        }
        response.setResult(responses);
        return ResponseEntity.ok(response);
    }

    // view waterstandard by ID
    @GetMapping("viewWaterstandard/{waterStandardId}")
    public ResponseEntity viewWaterStandard(@PathVariable("waterStandardId") long waterStandardId){
        APIResponse<WaterStandardResponse> response = new APIResponse<>();
        WaterStandardResponse waterStandardResponse = new WaterStandardResponse();
        WaterStandard waterStandard = adminService.getWaterStandardByID(waterStandardId);

        waterStandardResponse.setWaterStandardID(waterStandard.getWaterStandardId());

        waterStandardResponse.setMax_pH_Standard(waterStandard.getMax_pH_Standard());
        waterStandardResponse.setMin_pH_Standard(waterStandard.getMin_pH_Standard());

        waterStandardResponse.setMaxTempStandard(waterStandard.getMaxTempStandard());
        waterStandardResponse.setMinTempStandard(waterStandard.getMinTempStandard());

        waterStandardResponse.setMinOxygenStandard(waterStandard.getMinOxygenStandard());
        waterStandardResponse.setMaxOxygenStandard(waterStandard.getMaxOxygenStandard());

        waterStandardResponse.setMaxHardnessStandard(waterStandard.getMaxHardnessStandard());
        waterStandardResponse.setMinHardnessStandard(waterStandard.getMinHardnessStandard());

        waterStandardResponse.setMinAmmoniaStandard(waterStandard.getMinAmmoniaStandard());
        waterStandardResponse.setMaxAmmoniaStandard(waterStandard.getMaxAmmoniaStandard());

        waterStandardResponse.setMinNitriteStandard(waterStandard.getMinNitriteStandard());
        waterStandardResponse.setMaxNitriteStandard(waterStandard.getMaxNitriteStandard());

        waterStandardResponse.setMinNitrateStandard(waterStandard.getMinNitrateStandard());
        waterStandardResponse.setMaxNitrateStandard(waterStandard.getMaxNitrateStandard());

        waterStandardResponse.setMaxCarbonateStandard(waterStandard.getMaxCarbonateStandard());
        waterStandardResponse.setMinCarbonateStandard(waterStandard.getMinCarbonateStandard());

        waterStandardResponse.setMaxCarbonDioxideStandard(waterStandard.getMaxCarbonDioxideStandard());
        waterStandardResponse.setMinCarbonDioxideStandard(waterStandard.getMinCarbonDioxideStandard());

        waterStandardResponse.setMaxSaltStandard(waterStandard.getMaxSaltStandard());
        waterStandardResponse.setMinSaltStandard(waterStandard.getMinSaltStandard());

        response.setResult(waterStandardResponse);
        return ResponseEntity.ok(response);

    }

    //delete waterstandard
    @DeleteMapping("delete/waterstandard/{waterStandardID}")
    public ResponseEntity deleteWaterStandard(@PathVariable("waterStandardID") long waterStandardID){
        APIResponse response = new APIResponse();

        adminService.deleteWaterStandard(waterStandardID);
        response.setResult("DELETED");
        return ResponseEntity.ok(response);

    }

    //create pondstandard
    @PostMapping("pondstandard/create")
    public ResponseEntity createPondStandard(@RequestBody PondStandardRequest request) {
        APIResponse<PondStandardResponse> response = new APIResponse<>();
        PondStandardResponse pondStandardResponse = new PondStandardResponse();
        PondStandard pondStandard = adminService.createPondStandard(request);

        pondStandardResponse.setPondStandardID(pondStandard.getPondStandardID());

        pondStandardResponse.setMaxDepth(pondStandard.getMaxDepth());
        pondStandardResponse.setMinDepth(pondStandard.getMinDepth());

        pondStandardResponse.setMaxArea(pondStandard.getMaxArea());
        pondStandardResponse.setMinArea(pondStandard.getMinArea());

        pondStandardResponse.setMaxAmountFish(pondStandard.getMaxAmountFish());
        pondStandardResponse.setMinAmountFish(pondStandard.getMinAmountFish());

        pondStandardResponse.setMaxSkimmerCount(pondStandard.getMaxSkimmerCount());
        pondStandardResponse.setMinSkimmerCount(pondStandard.getMinSkimmerCount());

        pondStandardResponse.setMaxVolume(pondStandard.getMaxVolume());
        pondStandardResponse.setMinVolume(pondStandard.getMinVolume());

        pondStandardResponse.setMinDrainCount(pondStandard.getMinDrainCount());
        pondStandardResponse.setMaxDrainCount(pondStandard.getMaxDrainCount());

        pondStandardResponse.setMaxPumpingCapacity(pondStandard.getMaxPumpingCapacity());
        pondStandardResponse.setMinPumpingCapacity(pondStandard.getMinPumpingCapacity());

        response.setResult(pondStandardResponse);
        return ResponseEntity.ok(response);
    }


    //update pondstandard
    @PutMapping("pondstandard/update/{pondStandardID}")
    public ResponseEntity updatePondStandard(@PathVariable long pondStandardID, @RequestBody PondStandardRequest request) {
        APIResponse<PondStandardResponse> response = new APIResponse<>();

        PondStandardResponse pondStandardResponse = new PondStandardResponse();
        PondStandard pondStandard = adminService.updatePondStandardByID(pondStandardID, request);


        pondStandardResponse.setPondStandardID(pondStandard.getPondStandardID());

        pondStandardResponse.setMaxDepth(pondStandard.getMaxDepth());
        pondStandardResponse.setMinDepth(pondStandard.getMinDepth());

        pondStandardResponse.setMaxArea(pondStandard.getMaxArea());
        pondStandardResponse.setMinArea(pondStandard.getMinArea());

        pondStandardResponse.setMaxAmountFish(pondStandard.getMaxAmountFish());
        pondStandardResponse.setMinAmountFish(pondStandard.getMinAmountFish());

        pondStandardResponse.setMaxSkimmerCount(pondStandard.getMaxSkimmerCount());
        pondStandardResponse.setMinSkimmerCount(pondStandard.getMinSkimmerCount());

        pondStandardResponse.setMaxVolume(pondStandard.getMaxVolume());
        pondStandardResponse.setMinVolume(pondStandard.getMinVolume());

        pondStandardResponse.setMinDrainCount(pondStandard.getMinDrainCount());
        pondStandardResponse.setMaxDrainCount(pondStandard.getMaxDrainCount());

        pondStandardResponse.setMaxPumpingCapacity(pondStandard.getMaxPumpingCapacity());
        pondStandardResponse.setMinPumpingCapacity(pondStandard.getMinPumpingCapacity());

        response.setResult(pondStandardResponse);

        return ResponseEntity.ok(response);
    }








    @GetMapping("viewPondstandard/{pondStandardID}")
    public ResponseEntity viewPondStandard(@PathVariable("pondStandardID") long pondStandardID){
        APIResponse<PondStandardResponse> response = new APIResponse<>();
        PondStandardResponse pondStandardResponse = new PondStandardResponse();
        PondStandard pondStandard = adminService.getPondStandardByID(pondStandardID);

        pondStandardResponse.setPondStandardID(pondStandard.getPondStandardID());

        pondStandardResponse.setMaxDepth(pondStandard.getMaxDepth());
        pondStandardResponse.setMinDepth(pondStandard.getMinDepth());

        pondStandardResponse.setMaxArea(pondStandard.getMaxArea());
        pondStandardResponse.setMinArea(pondStandard.getMinArea());

        pondStandardResponse.setMaxAmountFish(pondStandard.getMaxAmountFish());
        pondStandardResponse.setMinAmountFish(pondStandard.getMinAmountFish());

        pondStandardResponse.setMaxSkimmerCount(pondStandard.getMaxSkimmerCount());
        pondStandardResponse.setMinSkimmerCount(pondStandard.getMinSkimmerCount());

        pondStandardResponse.setMaxVolume(pondStandard.getMaxVolume());
        pondStandardResponse.setMinVolume(pondStandard.getMinVolume());

        pondStandardResponse.setMaxDrainCount(pondStandard.getMaxDrainCount());
        pondStandardResponse.setMinDrainCount(pondStandard.getMinDrainCount());

        pondStandardResponse.setMaxPumpingCapacity(pondStandard.getMaxPumpingCapacity());
        pondStandardResponse.setMinPumpingCapacity(pondStandard.getMinPumpingCapacity());

        response.setResult(pondStandardResponse);
        return ResponseEntity.ok(response);


    }

    @DeleteMapping("deletePondStandard/{pondStandardID}")
    public ResponseEntity deletePondStandard(@PathVariable("pondStandardID") long pondStandardID){
        APIResponse response = new APIResponse();
        adminService.deletePondStandard(pondStandardID);
        response.setResult("DELETED");
        return ResponseEntity.ok(response);
    }

    @GetMapping("viewall/pondstandard")
    public ResponseEntity viewAllPondStandard(){
        APIResponse<List<PondStandard>> response = new APIResponse<>();
        List<PondStandard> pondStandardList = adminService.getAllPondStandard();
        response.setResult(pondStandardList);
        return ResponseEntity.ok(response);
    }


    @PutMapping("restoreAccount/{accountID}")
    public ResponseEntity unbanAccount(@PathVariable("accountID") long accountID){
        Account account = authenticationService.restoreAccount(accountID);
        return ResponseEntity.ok("Restore account id: " + accountID + " successfully");
    }

    @DeleteMapping("deleteAccount/{accountID}")
    public ResponseEntity deleteAccount(@PathVariable("accountID") long accountID){
        Account account = authenticationService.deleteAccount(accountID);
        return ResponseEntity.ok("Deleted account id: " + accountID + " successfully");
    }

}
