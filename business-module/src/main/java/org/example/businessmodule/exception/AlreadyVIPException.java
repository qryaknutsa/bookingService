package org.example.businessmodule.exception;

public class AlreadyVIPException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Этот билет уже имеет тип VIP.";
    }
}
