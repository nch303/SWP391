package koicare.koiCareProject.APIcontroller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.KoiFishRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiFishResponse;
import koicare.koiCareProject.dto.response.KoiVarietyResponse;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.repository.PondRepository;
import koicare.koiCareProject.service.KoiFishService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("koifish")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiFishController {

    @Autowired
    KoiFishService koiFishService;

    @Autowired
    PondRepository pondRepository;

    @Autowired
    ModelMapper modelMapper;

    //tạo cá koi
    @PostMapping("create")
    public APIResponse<KoiFishResponse> createKoiFish(@RequestBody KoiFishRequest request){
        APIResponse<KoiFishResponse> apiResponse = new APIResponse<>();

        KoiFishResponse koiFishResponse = modelMapper.map(koiFishService.createKoiFish(request), KoiFishResponse.class);
        koiFishResponse.setPondID(request.getPondID());
        koiFishResponse.setKoiVarietyID(request.getKoiVarietyID());

        apiResponse.setResult(koiFishResponse);

        return apiResponse;
    }

    //lấy lên danh sách cá koi theo MemberID
    @GetMapping("")
    public List<KoiFishResponse> getKoiFishes(){
        List<KoiFish> koiFishes = koiFishService.getKoiFishes();
        List<KoiFishResponse> koiFishResponses = koiFishes.stream()
                .map(koiFish -> modelMapper.map(koiFish, KoiFishResponse.class))
                .collect(Collectors.toList());
        return koiFishResponses;
    }

    //lấy lên cá koi theo koiFishID
    @GetMapping("{koiFishID}")
    public APIResponse<KoiFishResponse> getKoiFish(@PathVariable("koiFishID") long koiFishID){
        APIResponse<KoiFishResponse> response = new APIResponse<>();

        KoiFishResponse koiFishResponse = modelMapper.map(koiFishService.getKoiFish(koiFishID), KoiFishResponse.class);
        response.setResult(koiFishResponse);
        return response;
    }

    //update cá koi
    @PutMapping("{koiFishID}")
    public APIResponse<KoiFishResponse> updateKoiFish(@PathVariable("koiFishID") long koiFishID, @RequestBody KoiFishRequest request){
        APIResponse<KoiFishResponse> response = new APIResponse<>();

        KoiFishResponse koiFishResponse = modelMapper.map(koiFishService.updateKoiFish(koiFishID, request), KoiFishResponse.class);
        response.setResult(koiFishResponse);

        return response;
    }

    //xóa cá khỏi danh sách
    @DeleteMapping("{koiFishID}")
    public APIResponse deleteKoiFish(@PathVariable("koiFishID") long koiFishID){
        APIResponse response = new APIResponse();

        response.setResult("Deleted a koifish successfully!!!");

        koiFishService.deleteKoiFish(koiFishID);
        return response;
    }
}
