package com.sportsbet.interfaces.dto;

import com.sportsbet.domain.TicketType;
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
public class TicketDTO {

    private TicketType ticketType;
    private int quantity;
    private double totalCost;
}
