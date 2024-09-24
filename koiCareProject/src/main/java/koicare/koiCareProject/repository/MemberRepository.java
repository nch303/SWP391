package koicare.koiCareProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import koicare.koiCareProject.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
