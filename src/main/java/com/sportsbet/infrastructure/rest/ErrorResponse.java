package com.sportsbet.infrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents an error response returned by the API.
 */
@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    /**
     * The main error message describing the error.
     */
    private String message;

    /**
     * A list of additional details about the error.
     */
    private List<String> details;
}
