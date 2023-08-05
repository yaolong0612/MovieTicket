package com.sportsbet.application.strategy.price;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.springframework.stereotype.Component;

/**
 * Implementation of the TicketPriceCalculator interface for calculating ticket prices for Adult tickets.
 */
@Component
public class AdultTicketPriceCalculator implements TicketPriceCalculator {

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketType getTicketType() {
        return TicketType.ADULT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ticket calculatePrice(Ticket ticket) {
        checkTicketType(ticket.getTicketType());
        var totalCost = ticket.getQuantity() * 25.00;
        ticket.setTotalCost(totalCost);
        return ticket;
    }
}
