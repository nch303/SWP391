package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.ProductTypeRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.ProductTypeResponse;
import koicare.koiCareProject.entity.ProductType;
import koicare.koiCareProject.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "api")
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @PostMapping("createProductType")
    public APIResponse<ProductTypeResponse> createProductType(@RequestBody ProductTypeRequest request){
        APIResponse<ProductTypeResponse> response = new APIResponse<>();

        ProductType productType = productTypeService.createProductType(request);
        ProductTypeResponse productTypeResponse = new ProductTypeResponse();
        productTypeResponse.setProductTypeID(productType.getProductTypeID());
        productTypeResponse.setProductTypeName(productType.getProductTypeName());

        response.setResult(productTypeResponse);
        return response;

    }
}
