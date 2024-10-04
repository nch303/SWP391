package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.ProductTypeRequest;
import koicare.koiCareProject.entity.ProductType;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.PostDetailRepository;
import koicare.koiCareProject.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ProductType updateProductType(ProductTypeRequest request, long productTypeID) {
        ProductType productType = productTypeRepository.findByProductTypeID(productTypeID);

        if (productType == null) {
            throw new AppException(ErrorCode.PRODUCT_TYPE_IS_NOT_EXISTED);
        }
        else{
            productType.setProductTypeName(request.getProductTypeName());
            return productTypeRepository.save(productType);
        }
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public void deleteProductType(long productTypeID) {
        ProductType productType = productTypeRepository.findByProductTypeID(productTypeID);
        if (productType == null) {
            throw new AppException(ErrorCode.PRODUCT_TYPE_IS_NOT_EXISTED);
        }
        else{
            productTypeRepository.delete(productType);
        }
    }
}
