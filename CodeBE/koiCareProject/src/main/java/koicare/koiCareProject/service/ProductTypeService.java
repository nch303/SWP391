package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.ProductTypeRequest;
import koicare.koiCareProject.entity.ProductType;
import koicare.koiCareProject.repository.PostDetailRepository;
import koicare.koiCareProject.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;


    @Autowired
    PostDetailRepository postDetailRepository;

    public ProductType createProductType(ProductTypeRequest request) {
        ProductType productType = new ProductType();
        productType.setProductTypeName(request.getProductTypeName());
        return productTypeRepository.save(productType);
    }
}
