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
    public APIResponse<PostDetailResponse> createPost(@RequestBody PostDetailRequest postDetailRequest) {
        APIResponse<PostDetailResponse> response = new APIResponse<>();
        PostDetailResponse postDetailResponse = modelMapper.map
                (postDetailService.createPostDetail(postDetailRequest), PostDetailResponse.class);
        postDetailResponse.setProducTypeID(postDetailRequest.getProducTypeID());
        postDetailResponse.setPaymentID(postDetailRequest.getPaymentID());
        postDetailResponse.setShopID(postDetailRequest.getShopID());
        postDetailResponse.setPriceID(postDetailRequest.getPriceID());

        response.setResult(postDetailResponse);
        return response;
    }

    @GetMapping("view")
    public List<PostDetailResponse>getAllPosts() {
        List<PostDetail> postDetails = postDetailService.getAllPostDetails();
        List<PostDetailResponse> postDetailResponses = postDetails.stream()
                .map(postDetail -> modelMapper.map(postDetail, PostDetailResponse.class)).collect(Collectors.toList());
        return postDetailResponses;
    }
}
