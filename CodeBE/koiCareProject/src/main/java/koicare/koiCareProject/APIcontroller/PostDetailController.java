package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.PostDetailRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.PondResponse;
import koicare.koiCareProject.dto.response.PostDetailResponse;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.entity.PostDetail;
import koicare.koiCareProject.service.PostDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/post")
@SecurityRequirement(name = "api")
public class PostDetailController {

    @Autowired
    private PostDetailService postDetailService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("create")
    public ResponseEntity createPost(@RequestBody PostDetailRequest postDetailRequest) {
        APIResponse<PostDetailResponse> response = new APIResponse<>();
        PostDetailResponse postDetailResponse = modelMapper.map
                (postDetailService.createPostDetail(postDetailRequest), PostDetailResponse.class);
        postDetailResponse.setProducTypeID(postDetailRequest.getProducTypeID());

        response.setResult(postDetailResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view")
    public ResponseEntity getAllPosts() {
        List<PostDetail> postDetails = postDetailService.getAllPostDetails();
        List<PostDetailResponse> postDetailResponses = postDetails.stream()
                .map(postDetail -> modelMapper.map(postDetail, PostDetailResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok(postDetailResponses);
    }

    @GetMapping("view/approved/{shopID}")
    public ResponseEntity getApprovedPosts(@PathVariable long shopID) {
        List<PostDetail> postDetails = postDetailService.getAllPostByShopID(shopID);
        List<PostDetailResponse> postDetailResponses = postDetails.stream()
                .map(postDetail -> modelMapper.map(postDetail, PostDetailResponse.class)).collect(Collectors.toList());

        return ResponseEntity.ok(postDetailResponses);
    }

    @GetMapping("view/pending/{shopID}")
    public ResponseEntity getPendingPosts(@PathVariable long shopID) {
        List<PostDetail> postDetails = postDetailService.getAllPendingPostByShopID(shopID);
        List<PostDetailResponse> postDetailResponses = postDetails.stream()
                .map(postDetail -> modelMapper.map(postDetail, PostDetailResponse.class)).collect(Collectors.toList());

        return ResponseEntity.ok(postDetailResponses);
    }
    

}
