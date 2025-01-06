package org.example.businessmodule.exception;

public class CustomNotFound extends RuntimeException {
    public CustomNotFound(String message) {
        super(message);
    }
}
