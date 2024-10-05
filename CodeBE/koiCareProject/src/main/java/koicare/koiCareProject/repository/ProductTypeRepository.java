package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    ProductType findByProductTypeID(long productTypeID);
}
