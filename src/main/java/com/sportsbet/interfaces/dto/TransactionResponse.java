package com.sportsbet.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Transaction information with tickets and total cost")
public class TransactionResponse {

    @Schema(description = "Unique id of the transaction")
    private Long transactionId;
    @Schema(description = "List of tickets")
    private List<TicketDTO> tickets;
    @Schema(description = "Total cost of the transaction")
    private double totalCost;
}
