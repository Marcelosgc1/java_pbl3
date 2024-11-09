package com.vendaingressos.problema3_gui.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {}
    public UserNotFoundException(String message) {
        super(message);
    }
}
