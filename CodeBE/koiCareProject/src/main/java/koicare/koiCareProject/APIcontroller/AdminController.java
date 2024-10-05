package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import koicare.koiCareProject.dto.request.PondStandardRequest;
import koicare.koiCareProject.dto.request.WaterStandardRequest;
import koicare.koiCareProject.dto.response.APIResponse;

import koicare.koiCareProject.dto.response.PondStandardResponse;
import koicare.koiCareProject.dto.response.WaterStandardResponse;
import koicare.koiCareProject.entity.PostDetail;
import koicare.koiCareProject.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@SecurityRequirement(name = "api")
public class AdminController {

    @Autowired
    private AdminService adminService;







    //POST CONTROLLER
    @GetMapping("post/view/pending")
    public List<PostDetail> getPendingPosts() {
        return adminService.getAllPendingPostDetails();
    }

    @PutMapping("post/view/{postID}")
    public APIResponse approvePost(@PathVariable long postID) {
        APIResponse response = new APIResponse();

        adminService.approvedPostDetail(postID);

        response.setResult("POST HAS BEEN APPROVED");
        return response;
    }

    @GetMapping("post/view/approved")
    public List<PostDetail> getApprovedPosts() {
        return adminService.getAllApprovedPostDetails();
    }


    //Standard Controller
    @PutMapping("waterstandard/update")
    public APIResponse<WaterStandardResponse> updateWaterStandard(@RequestBody WaterStandardRequest request){
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
        return response;
    }


    @PutMapping("pondstandard/update")
    public APIResponse<PondStandardResponse> updatePondStandard(@RequestBody PondStandardRequest request){
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

        return response;
    }
}