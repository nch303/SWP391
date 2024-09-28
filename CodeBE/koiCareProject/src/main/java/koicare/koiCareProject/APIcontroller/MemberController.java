package koicare.koiCareProject.APIcontroller;

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
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private PondService pondService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public APIResponse<Member> createMember(@RequestBody MemberCreationRequest request) {
        APIResponse<Member> response = new APIResponse<>();

        response.setResult(memberService.createMember(request));
        return response;
    }


    @GetMapping("{memberID}")
    public List<PondResponse> viewPonds(@PathVariable("memberID") long memberID) {
        List<Pond> ponds = memberService.getPondsByMemberId(memberID);

        List<PondResponse> pondResponses = new ArrayList<>();
        ponds.forEach(p -> pondResponses.add(modelMapper.map(p, PondResponse.class)));

        return pondResponses;
    }


}
