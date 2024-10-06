package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.MemberPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPackageRepository extends JpaRepository<MemberPackage, Long> {
    MemberPackage findByPackageID(long packageID);
}
