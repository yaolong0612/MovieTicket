package com.sportsbet.application.strategy.price;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implementation of the TicketPriceCalculator interface for calculating ticket prices for Children tickets.
 */
@Component
public class ChildrenTicketPriceCalculator implements TicketPriceCalculator {

    @Value("${application.ticket.price.children}")
    private double price;

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketType getTicketType() {
        return TicketType.CHILDREN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ticket calculatePrice(Ticket ticket) {
        checkTicketType(ticket.getTicketType());
        var totalCost = ticket.getQuantity() * 5.00;
        ticket.setTotalCost(totalCost);
        return ticket;
    }
}
