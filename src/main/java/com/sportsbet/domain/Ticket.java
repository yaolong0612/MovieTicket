package com.sportsbet.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {

    private TicketType ticketType;
    private int quantity;
    private double totalCost;

}
