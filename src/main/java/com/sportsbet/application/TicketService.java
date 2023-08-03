package com.sportsbet.application;

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

    /**
     * Determines the ticket type based on the given age.
     *
     * @param age The age of the customer.
     * @return The ticket type corresponding to the given age.
     */
    public TicketType determineTicketTypeByAge(int age) {
        if (age < 11) {
            return TicketType.CHILDREN;
        } else if (age < 18) {
            return TicketType.TEEN;
        } else if (age < 65) {
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
        var costPerTicket = 0.0;
        switch (ticket.getTicketType()) {
            case ADULT:
                costPerTicket = 25.00;
                break;
            case SENIOR:
                costPerTicket = 25.00 * 0.70; // 30% cheaper
                break;
            case TEEN:
                costPerTicket = 12.00;
                break;
            case CHILDREN:
                costPerTicket = 5.00;
                if (ticket.getQuantity() >= 3) {
                    costPerTicket *= 0.75; // 25% discount
                }
                break;
            default:
                costPerTicket = 0.0;
        }
        return ticket.getQuantity() * costPerTicket;
    }
}
