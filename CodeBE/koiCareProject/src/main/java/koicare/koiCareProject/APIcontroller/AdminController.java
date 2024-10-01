package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.PostDetailResponse;
import koicare.koiCareProject.entity.PostDetail;
import koicare.koiCareProject.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@SecurityRequirement(name = "api")
public class AdminController {

    @Autowired
    private AdminService adminService;



    @GetMapping("view/pending")
    public List<PostDetail> getPendingPosts() {
        return adminService.getAllPendingPostDetails();
    }

    @PutMapping("view/{postID}")
    public APIResponse approvePost(@PathVariable long postID) {
        APIResponse response = new APIResponse();

        adminService.approvedPostDetail(postID);

        response.setResult("POST HAS BEEN APPROVED");
        return response;
    }
}
