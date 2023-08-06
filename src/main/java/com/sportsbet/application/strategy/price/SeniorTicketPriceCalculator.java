package com.sportsbet.application.strategy.price;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implementation of the TicketPriceCalculator interface for calculating ticket prices for senior tickets.
 */
@Component
public class SeniorTicketPriceCalculator implements TicketPriceCalculator {

    @Value("${application.ticket.price.senior}")
    private double price;

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketType getTicketType() {
        return TicketType.SENIOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ticket calculatePrice(Ticket ticket) {
        checkTicketType(ticket.getTicketType());
        var totalCost = ticket.getQuantity() * price;
        ticket.setTotalCost(totalCost);
        return ticket;
    }
}
