package com.sportsbet.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "Transaction request with transaction id and customer information list")
public class TransactionRequest {

    @Positive(message = "Transaction ID should be a positive integer")
    @NotNull(message = "Please provide transaction id")
    @Schema(description = "Unique id of the transaction", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long transactionId;

    @Valid
    @NotEmpty(message = "Customers cannot be empty")
    @Schema(description = "List of customer information in the transaction", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<CustomerDTO> customers;
}
