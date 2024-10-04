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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@SecurityRequirement(name = "api")
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @PostMapping("productType/create")
    public APIResponse<ProductTypeResponse> createProductType(@RequestBody ProductTypeRequest request){
        APIResponse<ProductTypeResponse> response = new APIResponse<>();

        ProductType productType = productTypeService.createProductType(request);
        ProductTypeResponse productTypeResponse = new ProductTypeResponse();
        productTypeResponse.setProductTypeID(productType.getProductTypeID());
        productTypeResponse.setProductTypeName(productType.getProductTypeName());

        response.setResult(productTypeResponse);
        return response;

    }

    @GetMapping("productType/view")
    public List<ProductTypeResponse> getAllProductType(){
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        List<ProductTypeResponse> productTypeResponses = new ArrayList<>();
        for (ProductType productType : productTypes) {
            ProductTypeResponse productTypeResponse = new ProductTypeResponse();
            productTypeResponse.setProductTypeID(productType.getProductTypeID());
            productTypeResponse.setProductTypeName(productType.getProductTypeName());

            productTypeResponses.add(productTypeResponse);
        }
        return productTypeResponses;
    }

    @PutMapping("productType/update/{productTypeID}")
    public APIResponse<ProductTypeResponse> updateProductType(@RequestBody ProductTypeRequest request, @PathVariable long productTypeID){
        APIResponse<ProductTypeResponse> response = new APIResponse<>();
        ProductType productType = productTypeService.updateProductType(request, productTypeID);
        ProductTypeResponse productTypeResponse = new ProductTypeResponse();
        productTypeResponse.setProductTypeID(productType.getProductTypeID());
        productTypeResponse.setProductTypeName(productType.getProductTypeName());
        response.setResult(productTypeResponse);
        return response;

    }

    @DeleteMapping("productType/delete/{productTypeID}")
    public APIResponse deleteProductType(@PathVariable long productTypeID){
        APIResponse response = new APIResponse();
        productTypeService.deleteProductType(productTypeID);

        response.setResult("DELETED SUCCESSFULLY");
        return response;
    }
}
