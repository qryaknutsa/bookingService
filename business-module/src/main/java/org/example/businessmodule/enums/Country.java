package org.example.businessmodule.enums;

import jakarta.json.bind.annotation.JsonbProperty;

public enum Country {
    @JsonbProperty("NORTH_KOREA")
    NORTH_KOREA("NORTH_KOREA"),
    @JsonbProperty("JAPAN")
    JAPAN("JAPAN"),
    @JsonbProperty("CHINA")
    CHINA("CHINA");

    private final String value;

    Country(String value) {
        this.value = value;
    }

}
