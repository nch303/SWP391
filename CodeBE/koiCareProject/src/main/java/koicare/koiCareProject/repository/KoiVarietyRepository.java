package koicare.koiCareProject.repository;

import koicare.koiCareProject.dto.request.KoiFishRequest;
import koicare.koiCareProject.entity.KoiVariety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KoiVarietyRepository extends JpaRepository<KoiVariety, Long> {

    KoiVariety getKoiVarietyByKoiVarietyID(Long koiVarietyID);
}
