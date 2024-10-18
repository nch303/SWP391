package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.PostDetailRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.PondResponse;
import koicare.koiCareProject.dto.response.PostDetailResponse;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.entity.PostDetail;
import koicare.koiCareProject.entity.ProductType;
import koicare.koiCareProject.repository.ProductTypeRepository;
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

    @Autowired
    ProductTypeRepository productTypeRepository;

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
        APIResponse<List<PostDetailResponse>> response = new APIResponse<>();
        List<PostDetail> postDetails = postDetailService.getAllPostDetails();
        List<PostDetailResponse> postDetailResponses = postDetails.stream()
                .map(postDetail -> {
                    PostDetailResponse postDetailResponse = modelMapper.map(postDetail, PostDetailResponse.class);

                    postDetailResponse.setProducTypeID(postDetail.getProductType().getProductTypeID());

                    return postDetailResponse;
                })
                .collect(Collectors.toList());
        response.setResult(postDetailResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view/approved")
    public ResponseEntity getApprovedPosts() {
        APIResponse<List<PostDetailResponse>> response = new APIResponse<>();
        List<PostDetail> postDetails = postDetailService.getAllPostByShopID();
        List<PostDetailResponse> postDetailResponses = postDetails.stream()
                .map(postDetail -> {
                    PostDetailResponse postDetailResponse = modelMapper.map(postDetail, PostDetailResponse.class);

                    postDetailResponse.setProducTypeID(postDetail.getProductType().getProductTypeID());

                    return postDetailResponse;
                })
                .collect(Collectors.toList());

        response.setResult(postDetailResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view/pending")
    public ResponseEntity getPendingPosts() {
        APIResponse<List<PostDetailResponse>> response = new APIResponse<>();
        List<PostDetail> postDetails = postDetailService.getAllPendingPostByShopID();
        List<PostDetailResponse> postDetailResponses = postDetails.stream()
                .map(postDetail -> {
                    PostDetailResponse postDetailResponse = modelMapper.map(postDetail, PostDetailResponse.class);

                    postDetailResponse.setProducTypeID(postDetail.getProductType().getProductTypeID());

                    return postDetailResponse;
                })
                .collect(Collectors.toList());
        response.setResult(postDetailResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view/postdetail/{postID}")
    public ResponseEntity getPostDetail(@PathVariable long postID) {
        APIResponse<PostDetailResponse> response = new APIResponse<>();
        PostDetail postDetail = postDetailService.getPostDetailById(postID);
        PostDetailResponse postDetailResponse = modelMapper.map(postDetail, PostDetailResponse.class);

        postDetailResponse.setProducTypeID(postDetail.getProductType().getProductTypeID());

        response.setResult(postDetailResponse);
        return ResponseEntity.ok(response);
    }


}
