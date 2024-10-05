package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.PostPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostPriceRepository extends JpaRepository<PostPrice, Long> {
    PostPrice findByPriceID(long priceID);
}
