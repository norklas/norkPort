package org.norklas.norkport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/navbar")
    public String getNavbar() {
        return "navbar";
    }
}
