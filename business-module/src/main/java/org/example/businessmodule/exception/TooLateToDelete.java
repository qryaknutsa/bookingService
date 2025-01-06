package org.example.businessmodule.exception;

public class TooLateToDelete extends RuntimeException {
    public TooLateToDelete(String message) {
        super(message);
    }
}
