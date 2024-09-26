package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.KoiStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoiStatusRepository extends JpaRepository<KoiStatus, Long> {
    KoiStatus getKoiStatusByKoiStatusID(long koiStatusID);
}
