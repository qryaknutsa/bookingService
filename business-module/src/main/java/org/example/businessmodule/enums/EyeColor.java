package org.example.businessmodule.enums;


//@JsonbEnum(EnumNamingStrategy.PROPERTY)

import jakarta.json.bind.annotation.JsonbProperty;

public enum EyeColor {
    @JsonbProperty("GREEN")
    GREEN("GREEN"),
    @JsonbProperty("RED")
    RED("RED"),
    @JsonbProperty("BLUE")
    BLUE("BLUE");

    private final String value;

    EyeColor(String value) {
        this.value = value;
    }

}
