package org.example.businessmodule.exception;

import java.util.List;

public class InvalidParameter extends RuntimeException {
    List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public InvalidParameter(List<String> messages) {
        this.messages = messages;
    }
}
