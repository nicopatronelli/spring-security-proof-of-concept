package com.example.springsecurityexample.model;

import com.example.springsecurityexample.refactor.Jwt;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationHeader {
    private final HttpServletRequest request;
    private final String authorizationHeader;

    public static AuthorizationHeader buildFrom(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        return new AuthorizationHeader(request, authorizationHeader);
    }

    public void handleAuthenticationIfPresent() {
        if (authorizationHeaderIsBearer(authorizationHeader)) {
            Jwt jwt = Jwt.createFrom(authorizationHeader);
            String username = jwt.getUsername();
            if (nonNull(username) && isNull(SecurityContextHolder.getContext().getAuthentication())) {
                UserDetails userDetails = User.userDetailsForUsernamePasswordAuthenticationToken(username);
                Collection<GrantedAuthority> authorities = jwt.getAuthorities();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, authorities
                );
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
    }

    //  self delegation
    private boolean authorizationHeaderIsBearer(String authorizationHeader) {
        return nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ");
    }
}
