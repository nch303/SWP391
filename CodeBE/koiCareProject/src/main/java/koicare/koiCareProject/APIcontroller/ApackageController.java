package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.ApackageRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.ApackageResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Apackage;
import koicare.koiCareProject.service.ApackageService;
import koicare.koiCareProject.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin/package")
@SecurityRequirement(name = "api")
public class ApackageController {

    @Autowired
    ApackageService apackageService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("create")
    public ResponseEntity creat(@RequestBody ApackageRequest request) {
        APIResponse<ApackageResponse> response = new APIResponse<>();
        Apackage apackage = apackageService.createApackage(request);
        ApackageResponse apackageResponse = modelMapper.map(apackage, ApackageResponse.class);
        apackageResponse.setAccountID(apackage.getAccount().getAccountID());
        apackageResponse.setId(apackage.getId());

        response.setResult(apackageResponse);

        return ResponseEntity.ok(response);

    }

    @GetMapping("viewAll")
    public ResponseEntity getAll() {
        APIResponse<List<ApackageResponse>> response = new APIResponse<>();

        List<Apackage> apackages = apackageService.getAllApackage();
        List<ApackageResponse> apackageResponses = apackages.stream()
                .map(apackage -> {
                    ApackageResponse apackageResponse = modelMapper.map(apackage, ApackageResponse.class);

                    // Set thêm các thuộc tính tùy chỉnh
                    apackageResponse.setId(apackage.getId());
                    apackageResponse.setAccountID(apackage.getAccount().getAccountID());

                    return apackageResponse;
                })
                .toList();

        response.setResult(apackageResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view/{packageID}")
    public ResponseEntity viewApackage(@PathVariable UUID packageID){
        APIResponse<ApackageResponse> response = new APIResponse<>();
        Apackage apackage = apackageService.getById(packageID);
        ApackageResponse apackageResponse = modelMapper.map(apackage,ApackageResponse.class);
        apackageResponse.setAccountID(apackage.getAccount().getAccountID());
        apackageResponse.setId(apackage.getId());

        response.setResult(apackageResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("view/memberpackage")
    public ResponseEntity getAllMemberPackage() {
        APIResponse<List<ApackageResponse>> response = new APIResponse<>();

        List<Apackage> apackages = apackageService.getAllMemberApackage();
        List<ApackageResponse> apackageResponses = apackages.stream()
                .map(apackage -> {
                    ApackageResponse apackageResponse = modelMapper.map(apackage, ApackageResponse.class);

                    // Set thêm các thuộc tính tùy chỉnh
                    apackageResponse.setId(apackage.getId());
                    apackageResponse.setAccountID(apackage.getAccount().getAccountID());

                    return apackageResponse;
                })
                .toList();

        response.setResult(apackageResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("view/shoppackage")
    public ResponseEntity getAllShopPackage() {
        APIResponse<List<ApackageResponse>> response = new APIResponse<>();

        List<Apackage> apackages = apackageService.getAllShopApackage();
        List<ApackageResponse> apackageResponses = apackages.stream()
                .map(apackage -> {
                    ApackageResponse apackageResponse = modelMapper.map(apackage, ApackageResponse.class);

                    // Set thêm các thuộc tính tùy chỉnh
                    apackageResponse.setId(apackage.getId());
                    apackageResponse.setAccountID(apackage.getAccount().getAccountID());

                    return apackageResponse;
                })
                .toList();

        response.setResult(apackageResponses);
        return ResponseEntity.ok(response);
    }


    @PutMapping("update/{packageID}")
    public ResponseEntity updateApackage(@PathVariable UUID packageID, @RequestBody ApackageRequest request) {
        APIResponse<ApackageResponse> response = new APIResponse<>();
        Apackage apackage = apackageService.update(packageID,request);
        ApackageResponse apackageResponse = modelMapper.map(apackage,ApackageResponse.class);
        apackageResponse.setAccountID(apackage.getAccount().getAccountID());
        apackageResponse.setId(packageID);

        response.setResult(apackageResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{packageID}")
    public ResponseEntity deleteApackage(@PathVariable UUID packageID){
        APIResponse response = new APIResponse();
        apackageService.delete(packageID);
        response.setResult("Delete package " + packageID + " successfully");
        return ResponseEntity.ok(response);
    }

}
