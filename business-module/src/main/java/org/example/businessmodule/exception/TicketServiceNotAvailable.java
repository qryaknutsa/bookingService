package org.example.businessmodule.exception;

public class TicketServiceNotAvailable extends RuntimeException {
    public TicketServiceNotAvailable(String message) {
        super(message);
    }
}
