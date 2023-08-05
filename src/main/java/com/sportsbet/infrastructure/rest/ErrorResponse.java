package com.sportsbet.infrastructure.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents an error response returned by the API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Error Response details")
public class ErrorResponse {

    /**
     * The main error message describing the error.
     */
    @Schema(description = "Error message")
    private String message;

    /**
     * A list of additional details about the error.
     */
    @Schema(description = "Error details")
    private List<String> details;
}
