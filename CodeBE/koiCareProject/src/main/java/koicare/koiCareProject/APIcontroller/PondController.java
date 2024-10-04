package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("api/pond")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class PondController {
    @Autowired
    private PondService pondService;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("create")
    public APIResponse<PondResponse> createPond(@RequestBody PondCreationRequest pondCreationRequest) {
        APIResponse<PondResponse> response = new APIResponse<>();

        PondResponse pondResponse = modelMapper.map(pondService.createPond(pondCreationRequest), PondResponse.class);
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
