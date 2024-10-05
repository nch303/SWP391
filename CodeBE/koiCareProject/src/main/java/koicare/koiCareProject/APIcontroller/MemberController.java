package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.PondResponse;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.service.MemberService;
import koicare.koiCareProject.service.PondService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PondService pondService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("ponds/{memberID}")
    public List<PondResponse> viewPonds(@PathVariable("memberID") long memberID) {
        List<Pond> ponds = memberService.getPondsByMemberId(memberID);

        List<PondResponse> pondResponses = new ArrayList<>();
        ponds.forEach(p -> pondResponses.add(modelMapper.map(p, PondResponse.class)));

        return pondResponses;
    }





}
