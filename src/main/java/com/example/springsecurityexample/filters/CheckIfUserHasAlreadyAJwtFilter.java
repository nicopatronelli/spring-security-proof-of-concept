package com.example.springsecurityexample.filters;

import com.example.springsecurityexample.model.AuthorizationHeader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CheckIfUserHasAlreadyAJwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        AuthorizationHeader.buildFrom(request).handleAuthenticationIfPresent();
        chain.doFilter(request, response);
    }

}
