package org.norklas.norkport;


import io.jstach.jstache.*;
import io.jstach.jstache.JStacheFlags.Flag;
import io.jstach.opt.spring.SpringJStacheConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@JStachePath(prefix = "templates/", suffix = ".mustache")
@JStacheConfig(using = SpringJStacheConfig.class)
public class NorkPortApplication {
    public static void main(String[] args) {
        SpringApplication.run(NorkPortApplication.class, args);
    }

}
