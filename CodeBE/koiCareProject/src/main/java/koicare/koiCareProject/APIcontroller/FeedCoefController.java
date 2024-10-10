package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.FeedCoefRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.FeedCoefResponse;
import koicare.koiCareProject.entity.FeedCoef;
import koicare.koiCareProject.service.FeedCoefService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/feedcoef")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class FeedCoefController {

    @Autowired
    FeedCoefService feedCoefService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("create")
    public ResponseEntity createFeedCoef(@RequestBody FeedCoefRequest request){
        APIResponse<FeedCoefResponse> response = new APIResponse<>();

        FeedCoef feedCoef = feedCoefService.createFeedCoef(request);
        FeedCoefResponse feedCoefResponse = modelMapper.map(feedCoef, FeedCoefResponse.class);
        response.setResult(feedCoefResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("viewall")
    public ResponseEntity viewAllFeedCoef() {
        APIResponse<List<FeedCoefResponse>> response = new APIResponse<>();
        List<FeedCoef> feedCoefs = feedCoefService.getAllFeedCoef();
        List<FeedCoefResponse> feedCoefResponses = feedCoefs.stream()
                .map(feedCoef -> modelMapper.map(feedCoef, FeedCoefResponse.class))
                .toList();

        response.setResult(feedCoefResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view/{feedCoefID}")
    public ResponseEntity viewFeedCoef(@PathVariable long feedCoefID) {
        APIResponse<FeedCoefResponse> response = new APIResponse<>();
        FeedCoef feedCoef = feedCoefService.getFeedCoefByFeedCoefID(feedCoefID);
        FeedCoefResponse feedCoefResponse = modelMapper.map(feedCoef, FeedCoefResponse.class);
        response.setResult(feedCoefResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{feedCoefID}")
    public ResponseEntity updateFeedCoef(@PathVariable long feedCoefID, @RequestBody FeedCoefRequest request) {
        APIResponse<FeedCoefResponse> response = new APIResponse<>();
        FeedCoef feedCoef = feedCoefService.updateFeedCoef(feedCoefID, request);
        FeedCoefResponse feedCoefResponse = modelMapper.map(feedCoef, FeedCoefResponse.class);
        response.setResult(feedCoefResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{feedCoefID}")
    public ResponseEntity deleteFeedCoef(@PathVariable long feedCoefID) {
        APIResponse response = new APIResponse<>();
        feedCoefService.deleteFeedCoef(feedCoefID);
        response.setResult("Delete FeedCoef Successfully!");
        return ResponseEntity.ok(response);
    }
}
