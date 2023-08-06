package com.sportsbet.domain;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration representing different types of movie tickets.
 */
public enum TicketType {
    ADULT("Adult"),
    SENIOR("Senior"),
    TEEN("Teen"),
    CHILDREN("Children");

    private final String value;

    TicketType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
