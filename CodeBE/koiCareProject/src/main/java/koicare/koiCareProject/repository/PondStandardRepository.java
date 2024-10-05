package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.PondStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PondStandardRepository extends JpaRepository<PondStandard, Long> {
    PondStandard findByPondStandardID(long pondStandardID);
}
