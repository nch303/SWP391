package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.Pond;
import org.springframework.data.jpa.repository.JpaRepository;
import koicare.koiCareProject.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member getMemberByMemberID(long memberID);

}
