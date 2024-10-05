package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByShopID(Long shopID);
}
