package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.BlogRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.entity.Blog;
import koicare.koiCareProject.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/blog")
@SecurityRequirement(name = "api")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody BlogRequest post) {
        APIResponse<Blog> response = new APIResponse<>();
        Blog blog = blogService.createPost(post);
        response.setResult(blog);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity getAllPosts() {
        APIResponse<List<Blog>> response = new APIResponse<>();
        List<Blog> blogs = blogService.getAllPosts();
        response.setResult(blogs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPostById(@PathVariable Long id) {
        APIResponse<Blog> response = new APIResponse<>();
        Blog blog = blogService.getPostById(id);
        response.setResult(blog);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePost(@PathVariable Long id, @RequestBody BlogRequest newPost) {
        APIResponse<Blog> response = new APIResponse<>();
        Blog blog = blogService.updatePost(id, newPost);
        response.setResult(blog);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        APIResponse<String> response = new APIResponse<>();
        blogService.deletePost(id);
        response.setResult("Delete blog " + id + " successfully");
        return ResponseEntity.ok(response);
    }
}
