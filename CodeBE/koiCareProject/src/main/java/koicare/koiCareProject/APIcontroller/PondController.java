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

import java.text.ParseException;
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
    private ModelMapper modelMapper;

    @PostMapping("create")
    public APIResponse<PondResponse> createPond(@RequestBody PondCreationRequest pondCreationRequest) throws ParseException {
        APIResponse<PondResponse> response = new APIResponse<>();

        PondResponse pondResponse = modelMapper.map(pondService.createPond(pondCreationRequest), PondResponse.class);
        response.setResult(pondResponse);

        return response;
    }

    @GetMapping
    public ResponseEntity getAllPonds() {
        List<Pond> ponds = pondService.getAllPonds();
        List<PondResponse> pondResponses = ponds.stream()
                .map(pond -> {
                    PondResponse pondResponse = modelMapper.map(pond, PondResponse.class);
                    pondResponse.setTotalWeight(pondService.calculateTotalWeight(pond.getPondID()));
                    return pondResponse;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(pondResponses);
    }


    @GetMapping("{pondID}")
    public ResponseEntity getPondById(@PathVariable("pondID") long pondID) {
        APIResponse<PondResponse> response = new APIResponse<>();

        PondResponse pondResponse = modelMapper.map(pondService.getPondById(pondID), PondResponse.class);
        pondResponse.setTotalWeight(pondService.calculateTotalWeight(pondID));
        response.setResult(pondResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{pondID}")
    public ResponseEntity updatePond(@PathVariable("pondID") long pondID, @RequestBody PondCreationRequest request) {
        APIResponse<PondResponse> response = new APIResponse<>();

        PondResponse pondResponse = modelMapper.map(pondService.updatePond(pondID, request), PondResponse.class);

        response.setResult(pondResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{pondID}")
    public ResponseEntity deletePond(@PathVariable("pondID") long pondID) {
        APIResponse response = new APIResponse();

        pondService.deletePond(pondID);
        response.setResult("Deleted successfully");
        return ResponseEntity.ok(response);
    }


}
