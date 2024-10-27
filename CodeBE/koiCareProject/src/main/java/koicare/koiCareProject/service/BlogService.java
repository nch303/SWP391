package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.BlogRequest;
import koicare.koiCareProject.entity.Blog;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.BlogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogPostRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Blog createPost(BlogRequest post) {
        post.setPublishedDate(LocalDate.now());
        Blog blog = modelMapper.map(post,Blog.class);
        return blogPostRepository.save(blog);
    }

    public List<Blog> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public Blog getPostById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_EXISTED));
    }

    public Blog updatePost(Long id, BlogRequest newPost) {
        return blogPostRepository.findById(id)
                .map(post -> {
                    post.setTitle(newPost.getTitle());
                    post.setContent(newPost.getContent());
                    return blogPostRepository.save(post);
                })
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_EXISTED));
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }
}
