package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.KoiStandardByVarietyRequest;
import koicare.koiCareProject.dto.request.KoiStandardFullRequest;
import koicare.koiCareProject.dto.request.KoiStandardRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiStandardFullResponse;
import koicare.koiCareProject.dto.response.KoiStandardResponse;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.service.KoiStandardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/koistandard")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiStandardController {

    @Autowired
    private KoiStandardService koiStandardService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KoiFishRepository koiFishRepository;


    // Lấy KoiStandard theo KoiVarietyID va Period
    @PostMapping("byPeriodandKoiVarietyID")
    public ResponseEntity getKoiStandard(@RequestBody KoiStandardRequest request){
        APIResponse<KoiStandardResponse> response =new APIResponse<>();

        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID());
        KoiStandard koiStandard = koiStandardService.getKoiStandardByVarietyAndPeriod(request);
        KoiStandardResponse koiStandardResponse = new KoiStandardResponse();
        if(koiFish.getKoiSex().equalsIgnoreCase("Male")){
            koiStandardResponse.setHiLength(koiStandard.getHiLengthMale());
            koiStandardResponse.setLowLength(koiStandard.getLowLengthMale());
            koiStandardResponse.setHiWeight(koiStandard.getHiWeightMale());
            koiStandardResponse.setLowWeigh(koiStandard.getLowWeightMale());
        }
        else{
            koiStandardResponse.setHiLength(koiStandard.getHiLengthFemale());
            koiStandardResponse.setLowLength(koiStandard.getLowLengthFemale());
            koiStandardResponse.setHiWeight(koiStandard.getHiWeightFemale());
            koiStandardResponse.setLowWeigh(koiStandard.getLowWeightFemale());
        }

        response.setResult(koiStandardResponse);

        return ResponseEntity.ok(response);
    }

    //lay koi standard theo variety
    @PostMapping("byKoiVarietyID")
    public ResponseEntity getKoiStandardByVariety(@RequestBody KoiStandardByVarietyRequest request){
        APIResponse<KoiStandardResponse> response =new APIResponse<>();


        KoiStandard koiStandard = koiStandardService.getKoiStandardByVariety(request);
        KoiStandardResponse koiStandardResponse = new KoiStandardResponse();
        if(request.getKoiSex().equalsIgnoreCase("Male")){
            koiStandardResponse.setHiLength(koiStandard.getHiLengthMale());
            koiStandardResponse.setLowLength(koiStandard.getLowLengthMale());
            koiStandardResponse.setHiWeight(koiStandard.getHiWeightMale());
            koiStandardResponse.setLowWeigh(koiStandard.getLowWeightMale());
        }
        else{
            koiStandardResponse.setHiLength(koiStandard.getHiLengthFemale());
            koiStandardResponse.setLowLength(koiStandard.getLowLengthFemale());
            koiStandardResponse.setHiWeight(koiStandard.getHiWeightFemale());
            koiStandardResponse.setLowWeigh(koiStandard.getLowWeightFemale());
        }

        response.setResult(koiStandardResponse);

        return ResponseEntity.ok(response);
    }

    @PostMapping("create")
    public ResponseEntity createKoiStandard(@RequestBody KoiStandardFullRequest request){
        APIResponse<KoiStandardFullResponse> response = new APIResponse<>();
        KoiStandard koiStandard = koiStandardService.createKoiStandard(request);
        KoiStandardFullResponse koiStandardFullResponse = modelMapper.map(koiStandard,KoiStandardFullResponse.class);

        response.setResult(koiStandardFullResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("viewall")
    public  ResponseEntity getAllKoiStandard(){
        APIResponse<List<KoiStandardFullResponse>> response = new APIResponse<>();

        List<KoiStandard> koiStandards = koiStandardService.getAllKoiStandard();
        List<KoiStandardFullResponse> koiStandardFullRespons = koiStandards.stream()
                .map(koiStandard -> modelMapper.map(koiStandard, KoiStandardFullResponse.class))
                .toList();

        response.setResult(koiStandardFullRespons);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view/{koiStandardID}")
    public ResponseEntity getKoiStandardByID(@PathVariable long koiStandardID){
        APIResponse<KoiStandardFullResponse> response = new APIResponse<>();

        KoiStandard koiStandard = koiStandardService.getKoiStandardByID(koiStandardID);
        KoiStandardFullResponse koiStandardFullResponse = modelMapper.map(koiStandard, KoiStandardFullResponse.class);
        response.setResult(koiStandardFullResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{koiStandardID}")
    public ResponseEntity updateKoiStandard(@RequestBody KoiStandardFullRequest request, @PathVariable long koiStandardID){
        APIResponse<KoiStandardFullResponse> response = new APIResponse<>();

        KoiStandard koiStandard = koiStandardService.updateKoiStandard(request,koiStandardID);
        KoiStandardFullResponse koiStandardFullResponse = modelMapper.map(koiStandard, KoiStandardFullResponse.class);
        response.setResult(koiStandardFullResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{koiStandardID}")
    public ResponseEntity deleteKoiStandard(@PathVariable long koiStandardID){
        APIResponse<String> response = new APIResponse<>();

        koiStandardService.deleteKoiStandard(koiStandardID);

        response.setResult("Delete koiStandard " + koiStandardID + " successfully");
        return ResponseEntity.ok(response);
    }
}
