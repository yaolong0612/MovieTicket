package com.sportsbet.infrastructure.error;

import com.sportsbet.infrastructure.rest.ResultCode;
import lombok.Getter;

/**
 * Custom exception class for service-level exceptions.
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 2359767895161832954L;

    /**
     * The result code associated with the exception.
     */
    @Getter
    private final ResultCode resultCode;

    /**
     * Constructs a new service exception with the specified error message.
     *
     * @param message The error message for the exception.
     */
    public ServiceException(String message) {
        super(message);
        this.resultCode = ResultCode.INTERNAL_SERVER_ERROR;
    }
}
