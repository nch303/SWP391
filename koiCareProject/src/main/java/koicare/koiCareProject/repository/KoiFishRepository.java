package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.KoiFish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KoiFishRepository extends JpaRepository<KoiFish, Long> {

    KoiFish getKoiFishByKoiFishID(Long KoiFishID);

}
