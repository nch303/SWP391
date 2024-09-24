package koicare.koiCareProject.APIcontroller;

import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public APIResponse<Member> createMember(@RequestBody MemberCreationRequest request) {
        APIResponse<Member> response = new APIResponse<>();

        response.setResult(memberService.createMember(request));
        return response;
    }

}
