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
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogPostRepository blogPostRepository;


    public List<BlogPost> getAllPosts(String title) {
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


    @PostMapping("/posts/new")
    public ResponseEntity<BlogPost> createPost(@RequestParam String title, @RequestParam String content) {
        BlogPost post = new BlogPost();
        post.setTitle(title);
        post.setContent(content);
        BlogPost savedPost = blogPostRepository.save(post);

        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
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
