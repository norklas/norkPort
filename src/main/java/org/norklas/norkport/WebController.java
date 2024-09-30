package org.norklas.norkport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private  BlogController blogController;

    @GetMapping("/")
    public String index() {
        return "index"; // Serves header.html from static resources
    }

    @GetMapping("/blog/posts")
    public String showPosts(Model model, @RequestParam(required = false) String title) {
        List<BlogPost> posts = blogController.getAllPosts(title);
        model.addAttribute("posts", posts);
        return "blog"; // This should match your template name
    }

    @GetMapping("/blog/posts/new")
    public String newPost(Model model) {
        return "blogpost";
    }
}

