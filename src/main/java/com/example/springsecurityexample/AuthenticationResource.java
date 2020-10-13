package com.example.springsecurityexample;

import com.example.springsecurityexample.model.AuthenticationRequest;
import com.example.springsecurityexample.model.AuthenticationResponse;
import com.example.springsecurityexample.refactor.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class AuthenticationResource {

    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {
        try {
            String username = authenticationRequest.getUsername();
            String password = authenticationRequest.getPassword();
            // authenticate() calls loadUserByUsername method in MyUserDetailsService
            Authentication authenticatedUser = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            Collection<? extends GrantedAuthority> authorities =
                    ((UserDetails)authenticatedUser.getPrincipal()).getAuthorities();
            final String jwt = Jwt.generateToken(username, authorities);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (BadCredentialsException exception) {
            throw new Exception("Incorrect username or password", exception);
        }
    }
}
