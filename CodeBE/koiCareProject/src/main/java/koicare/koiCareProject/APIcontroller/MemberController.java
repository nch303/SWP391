package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.dto.response.MemberResponse;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.service.MemberService;
import koicare.koiCareProject.service.PondService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/member")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class MemberController {
    @Autowired
    private MemberService memberService;


    @PutMapping("update")
    public ResponseEntity updateMember(@RequestBody MemberCreationRequest request){
        Member member = memberService.updateMember(request);
        return ResponseEntity.ok("Update successfully");
    }




}
