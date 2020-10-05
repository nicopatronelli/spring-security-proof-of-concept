package com.example.springsecurityexample;

import com.example.springsecurityexample.models.AuthenticationRequest;
import com.example.springsecurityexample.models.AuthenticationResponse;
import com.example.springsecurityexample.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException exception) {
            throw new Exception("Incorrect username or password", exception);
        }
        // if the authentication is succesful...
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        /* generateToken only needs the userName and we just got it. Then
           use a UserDetailsService to get the UserDetails is redundant, at
           least we would need more info about the user.
         */
        System.out.println("The user details are: " + userDetails.getUsername() + " and " + userDetails.getPassword());
        final String jwt = jwtUtil.generateToken(userDetails);

        // ResponseEntity.ok returns a response with 200 status code
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
