package com.sportsbet.infrastructure.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Global exception handler that handles and translates exceptions to appropriate error responses.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionTranslator {

    /**
     * Handles the exception when there is a method argument type mismatch in a request.
     *
     * @param e The MethodArgumentTypeMismatchException that occurred.
     * @return ResponseEntity containing the error response with details about the type mismatch.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleError(MethodArgumentTypeMismatchException e) {
        log.warn("Method Argument Type Mismatch", e);
        var details = Arrays.asList(String.format("Method Argument Type Mismatch: %s", e.getName()));

        var errorResponse = ErrorResponse.builder().message(ResultCode.PARAM_TYPE_ERROR.getMsg()).details(details)
            .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handles the exception when there are validation errors in method arguments in a request.
     *
     * @param ex The MethodArgumentNotValidException that occurred.
     * @return ResponseEntity containing the error response with details about the validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        log.warn("Method Argument Not Valid", ex);
        var details = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        var errorResponse = ErrorResponse.builder().message(ResultCode.PARAM_VALID_ERROR.getMsg()).details(details)
            .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handles the exception when the HTTP message in a request is not readable.
     *
     * @param ex The HttpMessageNotReadableException that occurred.
     * @return ResponseEntity containing the error response with details about the message not being readable.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleError(HttpMessageNotReadableException ex) {
        log.error("Message Not Readable", ex);
        var details = Arrays.asList(ex.getMessage());
        var errorResponse = ErrorResponse.builder().message(ResultCode.MSG_NOT_READABLE.getMsg()).details(details)
            .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handles the exception when an internal server error occurs.
     *
     * @param ex The Throwable that occurred.
     * @return ResponseEntity containing the error response with details about the internal server error.
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleError(Throwable ex) {
        log.error("Internal Server Error", ex);
        var details = Arrays.asList(ex.getMessage());
        var errorResponse = ErrorResponse.builder().message(ResultCode.INTERNAL_SERVER_ERROR.getMsg()).details(details)
            .build();
        return ResponseEntity.internalServerError().body(errorResponse);
    }

}
