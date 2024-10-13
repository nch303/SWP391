package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Shop;
import koicare.koiCareProject.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shop")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PutMapping("update")
    public ResponseEntity updateMember(@RequestBody MemberCreationRequest request){
        Shop shop   = shopService.updateShop(request);

        return ResponseEntity.ok("Update successfully");
    }
}
