package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.ProductTypeRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.PostPriceResponse;
import koicare.koiCareProject.dto.response.ProductTypeResponse;
import koicare.koiCareProject.entity.PostPrice;
import koicare.koiCareProject.entity.ProductType;
import koicare.koiCareProject.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@SecurityRequirement(name = "api")
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @PostMapping("productType/create")
    public ResponseEntity createProductType(@RequestBody ProductTypeRequest request){
        APIResponse<ProductTypeResponse> response = new APIResponse<>();

        ProductType productType = productTypeService.createProductType(request);
        ProductTypeResponse productTypeResponse = new ProductTypeResponse();
        productTypeResponse.setProductTypeID(productType.getProductTypeID());
        productTypeResponse.setProductTypeName(productType.getProductTypeName());

        response.setResult(productTypeResponse);
        return ResponseEntity.ok(response);

    }

    @GetMapping("productType/view")
    public ResponseEntity getAllProductType(){
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        List<ProductTypeResponse> productTypeResponses = new ArrayList<>();
        for (ProductType productType : productTypes) {
            ProductTypeResponse productTypeResponse = new ProductTypeResponse();
            productTypeResponse.setProductTypeID(productType.getProductTypeID());
            productTypeResponse.setProductTypeName(productType.getProductTypeName());

            productTypeResponses.add(productTypeResponse);
        }
        return ResponseEntity.ok(productTypeResponses);
    }

    @PutMapping("productType/update/{productTypeID}")
    public ResponseEntity updateProductType(@RequestBody ProductTypeRequest request, @PathVariable long productTypeID){
        APIResponse<ProductTypeResponse> response = new APIResponse<>();
        ProductType productType = productTypeService.updateProductType(request, productTypeID);
        ProductTypeResponse productTypeResponse = new ProductTypeResponse();
        productTypeResponse.setProductTypeID(productType.getProductTypeID());
        productTypeResponse.setProductTypeName(productType.getProductTypeName());
        response.setResult(productTypeResponse);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("productType/delete/{productTypeID}")
    public ResponseEntity deleteProductType(@PathVariable long productTypeID){
        APIResponse response = new APIResponse();
        productTypeService.deleteProductType(productTypeID);

        response.setResult("DELETED SUCCESSFULLY");
        return ResponseEntity.ok(response);
    }
}
