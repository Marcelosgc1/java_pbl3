package com.vendaingressos.problema3_gui.exceptions;

public class NoTicketException extends RuntimeException {

    public NoTicketException() {}
    public NoTicketException(String message) {
        super(message);
    }
}
