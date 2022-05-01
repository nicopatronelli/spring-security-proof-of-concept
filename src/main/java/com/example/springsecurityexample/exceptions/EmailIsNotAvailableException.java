package com.example.springsecurityexample.exceptions;

public class EmailIsNotAvailableException extends Exception {
    public EmailIsNotAvailableException() {
        super("The email is not available.");
    }
}
