package com.example.springsecurityexample;

import com.example.springsecurityexample.model.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SignUpResource {
    private MyUserDetailsService userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerANewUser(@RequestBody SignUpRequest signUpRequest) throws Exception {
        String username = signUpRequest.getUsername();
        String password = signUpRequest.getPassword();
        userDetailsService.registerANewUser(username, password);
        return ResponseEntity.ok("The user has been registered.");
    }
}
