package koicare.koiCareProject.service;

import koicare.koiCareProject.entity.PostDetail;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.PostDetailRepository;
import koicare.koiCareProject.repository.PostPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private PostDetailRepository postDetailRepository;

    public List<PostDetail> getAllPendingPostDetails(){
        return postDetailRepository.findByPostStatus(false);
    }


    public PostDetail approvedPostDetail(Long postID){
        PostDetail postDetail = postDetailRepository.findByPostID(postID);

        if (postDetail == null) {
            throw new AppException(ErrorCode.POST_DOES_NOT_EXIST);
        }
        else{
            postDetail.setPostStatus(true);
            return postDetailRepository.save(postDetail);
        }


    }
}
