package com.vendaingressos.problema3_gui.exceptions;

public class AlreadyExistingReviewException extends RuntimeException {

    public AlreadyExistingReviewException() {}
    public AlreadyExistingReviewException(String message) {
        super(message);
    }
}
