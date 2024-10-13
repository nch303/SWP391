package koicare.koiCareProject.APIcontroller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.TempCoefRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.TempCoefResponse;
import koicare.koiCareProject.entity.TempCoef;
import koicare.koiCareProject.repository.TempCoefRepository;
import koicare.koiCareProject.service.TempCoefService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tempcoef")
@SecurityRequirement(name = "api")
public class TempCoefController {

    @Autowired
    private TempCoefService tempCoefService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("create")
    public ResponseEntity createTempCoef(@RequestBody TempCoefRequest request) {
        APIResponse<TempCoefResponse> response = new APIResponse<>();
        TempCoef tempCoef = tempCoefService.createTempCoef(request);
        TempCoefResponse tempCoefResponse = modelMapper.map(tempCoef, TempCoefResponse.class);
        response.setResult(tempCoefResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("viewall")
    public ResponseEntity viewAllTempCoef() {
        APIResponse<List<TempCoefResponse>> response = new APIResponse<>();
        List<TempCoef> tempCoefs = tempCoefService.getAllTempCoef();
        List<TempCoefResponse> tempCoefResponses = tempCoefs.stream()
                .map(tempCoef -> modelMapper.map(tempCoef, TempCoefResponse.class))
                .toList();

        response.setResult(tempCoefResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view/{tempID}")
    public ResponseEntity viewTempCoef(@PathVariable long tempID) {
        APIResponse<TempCoefResponse> response = new APIResponse<>();
        TempCoef tempCoef = tempCoefService.getTempCoefByTempID(tempID);
        TempCoefResponse tempCoefResponse = modelMapper.map(tempCoef, TempCoefResponse.class);
        response.setResult(tempCoefResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{tempID}")
    public ResponseEntity updateTempCoef(@PathVariable long tempID, @RequestBody TempCoefRequest request) {
        APIResponse<TempCoefResponse> response = new APIResponse<>();
        TempCoef tempCoef = tempCoefService.updateTempCoef(tempID, request);
        TempCoefResponse tempCoefResponse = modelMapper.map(tempCoef, TempCoefResponse.class);
        response.setResult(tempCoefResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{tempID}")
    public ResponseEntity deleteTempCoef(@PathVariable long tempID) {
        APIResponse response = new APIResponse<>();
        tempCoefService.deleteTempCoef(tempID);
        response.setResult("Delete TempCoef Successfully!");
        return ResponseEntity.ok(response);
    }

}
