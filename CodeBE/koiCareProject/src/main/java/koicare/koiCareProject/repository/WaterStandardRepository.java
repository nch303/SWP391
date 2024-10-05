package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.WaterStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterStandardRepository extends JpaRepository<WaterStandard, Long> {
    WaterStandard findByWaterStandardId(long waterStandardId);
}
