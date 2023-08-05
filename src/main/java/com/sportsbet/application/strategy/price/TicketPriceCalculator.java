package com.sportsbet.application.strategy.price;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import com.sportsbet.infrastructure.error.ServiceException;

/**
 * The TicketPriceCalculator interface represents a strategy for calculating the price base on ticket information.
 */
public interface TicketPriceCalculator {

    /**
     * Gets the ticket type that this calculator is associated with.
     *
     * @return The ticket type.
     */
    TicketType getTicketType();

    /**
     * Calculates the ticket total cost based on ticket information
     *
     * @param ticket The information of the ticket
     * @return Ticket with total cost.
     */
    Ticket calculatePrice(Ticket ticket);

    /**
     * Checks if the provided ticket type matches the ticket type associated with this calculator.
     *
     * @param ticketType The ticket type to check.
     * @throws ServiceException if the ticket type does not match the calculator's ticket type.
     */
    default void checkTicketType(TicketType ticketType) {
        if (ticketType != getTicketType()) {
            throw new ServiceException("Ticket price calculator not valid");
        }
    }
}
