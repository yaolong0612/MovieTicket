package com.sportsbet.application.strategy.price;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;

/**
 * Implementation of the TicketPriceCalculator interface for testing purpose.
 */
public class TestTicketPriceCalculator implements TicketPriceCalculator {

    @Override
    public TicketType getTicketType() {
        return null;
    }

    @Override
    public Ticket calculatePrice(Ticket ticket) {
        return null;
    }
}
