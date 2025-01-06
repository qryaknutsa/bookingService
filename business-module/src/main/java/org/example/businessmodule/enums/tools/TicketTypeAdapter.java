package org.example.businessmodule.enums.tools;

import jakarta.json.bind.adapter.JsonbAdapter;
import org.example.businessmodule.enums.TicketType;

public class TicketTypeAdapter implements JsonbAdapter<TicketType, String> {
    @Override
    public TicketType adaptFromJson(String value) {
        if (value == null) {
            return null;
        }
        try {
            return TicketType.valueOf(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String adaptToJson(TicketType ticketType) {
        return ticketType != null ? ticketType.name() : null;
    }
}

