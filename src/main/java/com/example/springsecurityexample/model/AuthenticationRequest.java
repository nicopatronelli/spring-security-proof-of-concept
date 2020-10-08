package com.example.springsecurityexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class AuthenticationRequest {
    private String username;
    private String password;
}
