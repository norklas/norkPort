package org.norklas.norkport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index"; // Serves header.html from static resources
    }
}

