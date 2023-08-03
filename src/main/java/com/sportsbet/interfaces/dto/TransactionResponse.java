package com.sportsbet.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO representing a transaction response with transactionId, ticket information, and total cost.
 * <p>
 * This class is used to transfer transaction response data between different layers of the application,
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    private Long transactionId;
    private List<TicketDTO> tickets;
    private double totalCost;
}
