package com.sportsbet.infrastructure.rest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing different result codes for handling exceptions and errors in the application.
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * Result code for a successful operation.
     */
    SUCCESS(HttpServletResponse.SC_OK, "Operation is Successful"),

    /**
     * Result code for the scenario when the HTTP message in a request cannot be read.
     */
    MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "Message Can't be Read"),

    /**
     * Result code for the scenario when an internal server error occurs.
     */
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),

    /**
     * Result code for the scenario when there is a parameter type mismatch in a request.
     */
    PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Type Mismatch"),

    /**
     * Result code for the scenario when there are parameter validation errors in a request.
     */
    PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Validation Error");

    /**
     * The numeric code representing the result.
     */
    private final int code;

    /**
     * The description message associated with the result code.
     */
    private final String msg;

}
