package koicare.koiCareProject.repository;


import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Pond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PondRepository extends JpaRepository<Pond, Long> {

    Pond getPondByPondID(Long pondID);

    List<Pond> findAllByMember(Member member);

}


