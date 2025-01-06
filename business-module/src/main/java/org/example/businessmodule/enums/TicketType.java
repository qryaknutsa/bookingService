package org.example.businessmodule.enums;


import jakarta.json.bind.annotation.JsonbProperty;

public enum TicketType {
    @JsonbProperty("CHEAP")
    CHEAP("CHEAP"),
    @JsonbProperty("BUDGETARY")
    BUDGETARY("BUDGETARY"),
    @JsonbProperty("USUAL")
    USUAL("USUAL"),
    @JsonbProperty("VIP")
    VIP("VIP");

    private final String value;

    TicketType(String value) {
        this.value = value;
    }

}
