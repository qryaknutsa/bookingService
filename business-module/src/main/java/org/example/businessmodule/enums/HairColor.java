package org.example.businessmodule.enums;

import jakarta.json.bind.annotation.JsonbProperty;

public enum HairColor {
    @JsonbProperty("RED")
    RED("RED"),
    @JsonbProperty("BLACK")
    BLACK("BLACK"),
    @JsonbProperty("BLUE")
    BLUE("BLUE"),
    @JsonbProperty("ORANGE")
    ORANGE("ORANGE"),
    @JsonbProperty("WHITE")
    WHITE("WHITE");

    private final String value;

    HairColor(String value) {
        this.value = value;
    }

}
