package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.KoiVariety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoiVarietyRepository extends JpaRepository<KoiVariety, Long> {
}
