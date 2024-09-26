package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.KoiStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoiStandardRepository extends JpaRepository<KoiStandard, Long> {
    KoiStandard getKoiStandardByKoiStandID(long koiStandID);
}
