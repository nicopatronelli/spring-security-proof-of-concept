package com.example.springsecurityexample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @RequestMapping("/")
    public String greeting() {
        return "<h4>Welcome stranger!</h4>";
    }

    @RequestMapping("/user")
    public String greetingToUser() {
        return "<h1>Welcome User!</h1>";
    }

    @RequestMapping("/admin")
    public String greetingToAdmin() {
        return "<h1>Welcome Admin!</h1>";
    }

}
