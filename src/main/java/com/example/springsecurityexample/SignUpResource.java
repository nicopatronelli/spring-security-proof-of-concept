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
    private UserSignUpService userSignUpService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerANewUser(@RequestBody SignUpRequest signUpRequest) throws Exception {
        String username = signUpRequest.getUsername();
        String password = signUpRequest.getPassword();
        String email = signUpRequest.getEmail();
        userSignUpService.registerANewUser(username, password, email);
        return ResponseEntity.ok("The user has been registered.");
    }
}
