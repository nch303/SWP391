package koicare.koiCareProject.APIcontroller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import koicare.koiCareProject.dto.request.KoiFishRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiFishResponse;
import koicare.koiCareProject.dto.response.KoiReportResponse;
import koicare.koiCareProject.dto.response.KoiVarietyResponse;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.repository.KoiVarietyRepository;
import koicare.koiCareProject.repository.PondRepository;
import koicare.koiCareProject.service.KoiFishService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/koifish")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiFishController {

    @Autowired
    private KoiFishService koiFishService;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private KoiVarietyRepository koiVarietyRepository;

    @Autowired
    private ModelMapper modelMapper;

    //tạo cá koi
    @PostMapping("create")
    public ResponseEntity createKoiFish(@Valid @RequestBody KoiFishRequest request) throws ParseException {
        APIResponse<KoiFishResponse> apiResponse = new APIResponse<>();

        KoiFishResponse koiFishResponse = modelMapper.map(koiFishService.createKoiFish(request), KoiFishResponse.class);
        koiFishResponse.setPondName(pondRepository.getPondByPondID(request.getPondID()).getPondName() );
        koiFishResponse.setKoiVariety(koiVarietyRepository.getKoiVarietyByKoiVarietyID(request.getKoiVarietyID()).getVarietyName() );

        apiResponse.setResult(koiFishResponse);

        return ResponseEntity.ok(apiResponse);
    }

    //lấy lên danh sách cá koi theo MemberID
    @GetMapping("")
    public ResponseEntity getKoiFishes(){
        List<KoiFish> koiFishes = koiFishService.getKoiFishes();
        List<KoiFishResponse> koiFishResponses = koiFishes.stream()
                .map(koiFish -> {
                    KoiFishResponse koiFishResponse = modelMapper.map(koiFish, KoiFishResponse.class);

                    koiFishResponse.setPondName(pondRepository.getPondByPondID(koiFish.getPond().getPondID()).getPondName());
                    koiFishResponse.setKoiVariety(koiVarietyRepository.getKoiVarietyByKoiVarietyID(koiFish.getKoiVariety().getKoiVarietyID()).getVarietyName() );
                    return koiFishResponse;
                })
                .collect(Collectors.toList());

        Collections.reverse(koiFishResponses);

        
        return ResponseEntity.ok(koiFishResponses);
    }

    //lấy lên cá koi theo koiFishID
    @GetMapping("{koiFishID}")
    public ResponseEntity getKoiFish(@PathVariable("koiFishID") long koiFishID){
        APIResponse<KoiFishResponse> response = new APIResponse<>();

        KoiFish koiFish = koiFishService.getKoiFish(koiFishID);
        KoiFishResponse koiFishResponse = modelMapper.map(koiFish, KoiFishResponse.class);
        koiFishResponse.setKoiVarietyID(koiFish.getKoiVariety().getKoiVarietyID());
        koiFishResponse.setPondID(koiFish.getPond().getPondID());

        response.setResult(koiFishResponse);
        return ResponseEntity.ok(response);
    }

    //lấy lên cá koi theo pondID
    @GetMapping("pond/{pondID}")
    public ResponseEntity getKoiFishByPondID(@PathVariable long pondID){
        APIResponse<List<KoiFishResponse>> response = new APIResponse<>();
        List<KoiFish> koiFishes = koiFishService.getKoiFishWithPondID(pondID);
        List<KoiFishResponse> koiFishResponses = koiFishes.stream()
                .map(koiFish -> {
                    KoiFishResponse koiFishResponse = modelMapper.map(koiFish, KoiFishResponse.class);
                    koiFishResponse.setPondName(pondRepository.getPondByPondID(koiFish.getPond().getPondID()).getPondName());
                    koiFishResponse.setKoiVariety(koiVarietyRepository.getKoiVarietyByKoiVarietyID(koiFish.getKoiVariety().getKoiVarietyID()).getVarietyName() );
                    return koiFishResponse;
                })
                .collect(Collectors.toList());
        response.setResult(koiFishResponses);
        return ResponseEntity.ok(response);

    }

    //update cá koi
    @PutMapping("{koiFishID}")
    public ResponseEntity updateKoiFish(@Valid @PathVariable("koiFishID") long koiFishID, @RequestBody KoiFishRequest request){
        APIResponse<KoiFishResponse> response = new APIResponse<>();

        KoiFishResponse koiFishResponse = modelMapper.map(koiFishService.updateKoiFish(koiFishID, request), KoiFishResponse.class);
        response.setResult(koiFishResponse);

        return ResponseEntity.ok(response);
    }

    //xóa cá khỏi danh sách
    @DeleteMapping("{koiFishID}")
    public ResponseEntity deleteKoiFish(@PathVariable("koiFishID") long koiFishID){
        APIResponse response = new APIResponse();

        response.setResult("Deleted a koifish successfully!!!");

        koiFishService.deleteKoiFish(koiFishID);
        return ResponseEntity.ok(response);
    }
}
