package koicare.koiCareProject.repository;


import koicare.koiCareProject.entity.Pond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PondRepository extends JpaRepository<Pond, Long> {

}
