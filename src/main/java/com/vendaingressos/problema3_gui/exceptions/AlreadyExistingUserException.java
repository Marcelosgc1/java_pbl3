package com.vendaingressos.problema3_gui.exceptions;

public class AlreadyExistingUserException extends Exception {
    public AlreadyExistingUserException(String message) {
        super(message);
    }
    public AlreadyExistingUserException() {}
}
