package com.example.springsecurityexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpRequest {
    private final String username;
    private final String password;
    private final String email;
}

