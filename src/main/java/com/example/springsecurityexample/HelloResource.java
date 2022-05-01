package com.example.springsecurityexample;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @RequestMapping("/secure/hello")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String securedHello() {
        return "Hello agent! This is a protected endpoint. If you are reading this means you are authenticated";
    }

    @RequestMapping("/public/hello")
    public String publicHello() {
        return "Hello world! You don't need to be authenticated to see this message";
    }

}
