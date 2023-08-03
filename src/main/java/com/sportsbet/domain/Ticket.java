package com.sportsbet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Ticket entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    private TicketType ticketType;
    private int quantity;
    private double totalCost;
}
