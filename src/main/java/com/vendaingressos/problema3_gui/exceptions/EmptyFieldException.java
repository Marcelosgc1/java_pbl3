package com.vendaingressos.problema3_gui.exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {}
    public EmptyFieldException(String message) {
        super(message);
    }
}
