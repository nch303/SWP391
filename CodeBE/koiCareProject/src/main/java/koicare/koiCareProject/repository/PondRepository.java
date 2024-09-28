package koicare.koiCareProject.repository;


import koicare.koiCareProject.entity.Pond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public interface PondRepository extends JpaRepository<Pond, Long> {

    Pond getPondByPondID(Long pondID);

//    @Query("From Pond where member.memberID=: memberID")
//    List<Pond> getPondByMemberID(@Param("memberID") Long memberID);



}


