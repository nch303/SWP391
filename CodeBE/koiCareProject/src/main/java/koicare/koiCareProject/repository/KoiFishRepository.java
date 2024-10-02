package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.Member;

import koicare.koiCareProject.entity.Pond;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KoiFishRepository extends JpaRepository<KoiFish, Long> {

    KoiFish getKoiFishByKoiFishID(long KoiFishID);

    List<KoiFish> findAllByMember(Member member);

    List<KoiFish> getAllByPond(Pond pond);


}
