package com.sportsbet.application;

import com.sportsbet.application.strategy.price.TicketPriceCalculator;
import com.sportsbet.application.strategy.price.TicketPriceCalculatorFactory;
import com.sportsbet.domain.Customer;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Application service for handling ticket operations.
 */
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketPriceCalculatorFactory ticketPriceCalculatorFactory;

    /**
     * Determines the ticket type based on the customer information.
     *
     * @param customer Customer information.
     * @return The ticket type corresponding to the customer information.
     */
    public TicketType determineTicketTypeByConsumerDetails(Customer customer) {
        if (customer.getAge() < 11) {
            return TicketType.CHILDREN;
        } else if (customer.getAge() < 18) {
            return TicketType.TEEN;
        } else if (customer.getAge() < 65) {
            return TicketType.ADULT;
        } else {
            return TicketType.SENIOR;
        }
    }

    /**
     * Calculates the total cost for a given ticket based on its ticket type and quantity.
     *
     * @param ticket The Ticket object for which the total cost needs to be calculated.
     * @return The total cost of the tickets of the given type and quantity.
     */
    public double calculateTicketTotalCost(Ticket ticket) {
        TicketPriceCalculator ticketPriceCalculator = ticketPriceCalculatorFactory.getCalculator(ticket.getTicketType());
        var totalCost = ticketPriceCalculator.calculatePrice(ticket).getTotalCost();
        if (ticket.getTicketType() == TicketType.SENIOR) {
            totalCost = totalCost * 0.7;
            return totalCost;
        }
        if (ticket.getTicketType() == TicketType.CHILDREN && ticket.getQuantity() >= 3) {
            totalCost = totalCost * 0.75;
        }
        return totalCost;
    }
}
