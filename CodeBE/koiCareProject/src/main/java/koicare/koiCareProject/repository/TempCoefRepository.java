package koicare.koiCareProject.repository;


import koicare.koiCareProject.entity.TempCoef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempCoefRepository extends JpaRepository<TempCoef, Long> {

     TempCoef getTempCoefByTempID(long tempID);
}
