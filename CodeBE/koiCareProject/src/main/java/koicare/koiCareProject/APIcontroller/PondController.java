package koicare.koiCareProject.APIcontroller;

import koicare.koiCareProject.dto.request.PondCreationRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiFishResponse;
import koicare.koiCareProject.dto.response.PondResponse;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.repository.PondRepository;
import koicare.koiCareProject.service.PondService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pond")
public class PondController {
    @Autowired
    private PondService pondService;

    @Autowired
    private PondRepository pondRepository;

//    @PostMapping
//    public APIResponse<Pond> createPond(@RequestBody PondCreationRequest request) {
//
//        APIResponse<Pond> response = new APIResponse<>();
//        response.setResult(pondService.createPond(request));
//        return response;
//    }

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("create")
    public APIResponse<PondResponse> createPond(@RequestBody PondCreationRequest pondCreationRequest) {
        APIResponse<PondResponse> response = new APIResponse<>();

        PondResponse pondResponse = modelMapper.map(pondService.createPond(pondCreationRequest), PondResponse.class);
        pondResponse.setMemberID(pondCreationRequest.getMemberID());
        response.setResult(pondResponse);

        return response;
    }
    @GetMapping
    public List<PondResponse> getAllPonds() {
        List<Pond> ponds = pondService.getAllPonds();
        List<PondResponse> pondResponses = ponds.stream()
                .map(Pond -> modelMapper.map(Pond, PondResponse.class)).collect(Collectors.toList());
        return pondResponses;
    }

//    @GetMapping("/view/{memberID}")
//    public List<PondResponse> viewPondsByMemberID(@PathVariable long memberID) {
//        List<Pond> ponds = pondService.getPondsByMemberID(memberID);
//        return ponds.stream()
//                .map(Pond -> modelMapper.map(Pond, PondResponse.class)).collect(Collectors.toList());
//    }

//    @GetMapping("/view/{memberID}")
//    public ResponseEntity<List<Pond>> getPondsByMemberID(@PathVariable("memberID") Long memberID) {
//        return new ResponseEntity<List<Pond>>(pondRepository.getPondByMemberID(memberID), HttpStatus.OK);
//    }





    @GetMapping("{pondID}")
    public APIResponse<PondResponse> getPondById(@PathVariable("pondID") long pondID) {
        APIResponse<PondResponse> response = new APIResponse<>();

        PondResponse pondResponse = modelMapper.map(pondService.getPondById(pondID), PondResponse.class);
        response.setResult(pondResponse);
        return response;
    }

    @PutMapping("{pondID}")
    public APIResponse<PondResponse> updatePond(@PathVariable("pondID") long pondID, @RequestBody PondCreationRequest request) {
        APIResponse<PondResponse> response = new APIResponse<>();

        PondResponse pondResponse = modelMapper.map(pondService.updatePond(pondID, request), PondResponse.class);

        response.setResult(pondResponse);
        return response;
    }
    @DeleteMapping("{pondID}")
    public APIResponse deletePond(@PathVariable("pondID") long pondID) {
        APIResponse response = new APIResponse();

        pondService.deletePond(pondID);
        response.setResult("Deleted successfully");
        return response;
    }


}
