package com.sportsbet.interfaces.dto;

import com.sportsbet.domain.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a ticket
 * <p>
 * This class is used to transfer ticket data between different layers of the application,
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Ticket information")
public class TicketDTO {

    @Schema(description = "Type of ticket")
    private TicketType ticketType;
    @Schema(description = "Quantity of ticket")
    private int quantity;
    @Schema(description = "Total cost of ticket")
    private double totalCost;
}
