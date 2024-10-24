package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.entity.KoiVariety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoiStandardRepository extends JpaRepository<KoiStandard, Long> {
    KoiStandard getKoiStandardByKoiVarietyAndPeriod(KoiVariety koiVariety, long Period);
    KoiStandard getKoiStandardBykoiStandID(long koiStandID);
}
