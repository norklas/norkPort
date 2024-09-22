package org.norklas.norkport;

import io.jstach.jstache.JStache;
import io.jstach.jstache.JStachePath;
import io.jstach.opt.spring.webmvc.JStachioModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public View index() {
        String name = "Nick";
        return JStachioModelView.of(new NameModel(name)); // Serves header.mustache from static resources
    }
}

