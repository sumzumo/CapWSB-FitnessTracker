package com.capgemini.wsb.fitnesstracker.user.api;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
