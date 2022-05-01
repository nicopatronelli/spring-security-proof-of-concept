package com.example.springsecurityexample.exceptions;

public class UserNameIsNotAvailableException extends Exception {
    public UserNameIsNotAvailableException() {
        super("The username is not available.");
    }
}
