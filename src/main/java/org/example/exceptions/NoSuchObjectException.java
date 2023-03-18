package org.example.exceptions;

public class NoSuchObjectException extends RuntimeException {

    public NoSuchObjectException(String message) {
        super(message);
    }
}
