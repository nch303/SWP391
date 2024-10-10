package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.FeedCoef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedCoefRepository extends JpaRepository<FeedCoef,Long> {
    FeedCoef getFeedCoefByFeedCoefID(long feedCoefID);
}
