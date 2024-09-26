package org.norklas.norkport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// API is built, and works, just need to build out a service and refactor to use jstachio
@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogPostRepository blogPostRepository;


    @GetMapping("/posts")
    public String showPosts(Model model, @RequestParam(required = false) String title) {
        List<BlogPost> posts = getAllPosts(title);
        model.addAttribute("posts", posts);
        return "blog"; // This should match your template name
    }

    private List<BlogPost> getAllPosts(String title) {
        try {
            if (title == null || title.isEmpty()) {
                return blogPostRepository.findAll();
            } else {
                return blogPostRepository.findByTitleContainingIgnoreCase(title);
            }
        } catch (Exception e) {
            // Log the exception (optional)
            return new ArrayList<>(); // Return an empty list in case of error
        }
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable("id") long id) {
        Optional<BlogPost> postData = blogPostRepository.findById(id);

        if (postData.isPresent()) {
            return new ResponseEntity<>(postData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost post) {
        try {
            BlogPost _post = blogPostRepository.save(new BlogPost(post.getTitle(), post.getContent()));
            return new ResponseEntity<>(_post, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable("id") long id, @RequestBody BlogPost post) {
        Optional<BlogPost> postData = blogPostRepository.findById(id);

        if (postData.isPresent()) {
            BlogPost _post = postData.get();
            _post.setTitle(post.getTitle());
            _post.setContent(post.getContent());
            return new ResponseEntity<>(blogPostRepository.save(_post), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<BlogPost> deletePost(@PathVariable("id") long id) {
        try {
            blogPostRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/posts")
    public ResponseEntity<BlogPost> deleteAllPosts() {
        try {
            blogPostRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
