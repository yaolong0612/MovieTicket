package com.sportsbet.interfaces.dto;

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

    private Long transactionId;
    private List<CustomerDTO> customers;
}
