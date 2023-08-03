package com.sportsbet.interfaces.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO representing a transaction request with transactionId and customer information.
 * <p>
 * This class is used to transfer transaction request data between different layers of the application,
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    @Positive(message = "Transaction ID should be a positive integer")
    private Long transactionId;
    @Valid
    @NotEmpty(message = "Customers cannot be empty")
    private List<CustomerDTO> customers;
}
