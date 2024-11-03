package com.capgemini.wsb.fitnesstracker.user.api;

public class UserNotFoundException extends RuntimeException {

    // Konstruktor z komunikatem o błędzie
    public UserNotFoundException(String message) {
        super(message);
    }
}
