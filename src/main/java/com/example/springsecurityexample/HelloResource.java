package com.example.springsecurityexample;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @RequestMapping("/hello")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String hello() {
        return "Hello world!";
    }

}
