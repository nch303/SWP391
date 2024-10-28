package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.ShopResponse;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Shop;
import koicare.koiCareProject.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shop")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("update")
    public ResponseEntity updateMember(@RequestBody MemberCreationRequest request){
        Shop shop   = shopService.updateShop(request);

        return ResponseEntity.ok("Update successfully");
    }

    @GetMapping("{shopID}")
    public ResponseEntity getShopByID(long shopID){
        APIResponse<ShopResponse> response = new APIResponse<>();

        Shop shop = shopService.getShopByID(shopID);
        ShopResponse shopResponse = modelMapper.map(shop,ShopResponse.class);
        response.setResult(shopResponse);
        return ResponseEntity.ok(response);

    }
}
