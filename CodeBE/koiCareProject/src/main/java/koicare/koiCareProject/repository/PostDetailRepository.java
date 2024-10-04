package koicare.koiCareProject.repository;

import jdk.jfr.Registered;
import koicare.koiCareProject.entity.PostDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Long> {

    PostDetail findByPostID(long postID);
    List<PostDetail> findByPostStatus(Boolean postStatus);


}
