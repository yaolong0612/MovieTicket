package com.sportsbet.application;

import com.sportsbet.application.strategy.discount.TicketDiscountCalculator;
import com.sportsbet.application.strategy.discount.TicketDiscountCalculatorFactory;
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
    private final TicketDiscountCalculatorFactory ticketDiscountCalculatorFactory;

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
     * Creates a new ticket with the given ticket type and quantity.
     *
     * @param ticketType The type of the ticket to create.
     * @param quantity   The quantity of the ticket to create.
     * @return The created ticket.
     */
    public Ticket createTicket(TicketType ticketType, int quantity) {
        var ticket = Ticket.builder().ticketType(ticketType).quantity(quantity).build();
        var ticketTotalCost = calculateTicketTotalCost(ticket);
        ticket.setTotalCost(ticketTotalCost);
        return ticket;
    }

    /**
     * Calculates the total cost for a given ticket based on its ticket type and quantity.
     *
     * @param ticket The Ticket object for which the total cost needs to be calculated.
     * @return The total cost of the tickets of the given type and quantity.
     */
    private double calculateTicketTotalCost(Ticket ticket) {
        TicketPriceCalculator ticketPriceCalculator = ticketPriceCalculatorFactory.getPriceCalculator(ticket);
        TicketDiscountCalculator ticketDiscountCalculator = ticketDiscountCalculatorFactory.getPriceCalculator(ticket);

        var totalCost = ticketPriceCalculator.calculatePrice(ticket).getTotalCost();
        var discountRate = ticketDiscountCalculator.calculateDiscountRate(ticket);

        return totalCost * discountRate;
    }
}
