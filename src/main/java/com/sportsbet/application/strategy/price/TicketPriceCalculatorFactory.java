package com.sportsbet.application.strategy.price;

import com.sportsbet.domain.Ticket;
import com.sportsbet.infrastructure.error.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Factory class for retrieving the appropriate ticket price calculator based on the ticket type.
 */
@Component
@RequiredArgsConstructor
public class TicketPriceCalculatorFactory {

    private final List<TicketPriceCalculator> calculators;

    /**
     * Gets the ticket price calculator associated with the specified ticket type.
     *
     * @param ticket The ticket for which to retrieve the calculator.
     * @return The ticket price calculator associated with the specified ticket type.
     * @throws ServiceException if no matching ticket price calculator is found for the specified ticket type.
     */
    public TicketPriceCalculator getPriceCalculator(Ticket ticket) {
        return calculators.stream()
            .filter(calculator -> calculator.getTicketType() == ticket.getTicketType())
            .findFirst()
            .orElseThrow(
                () -> new ServiceException("No matching ticket price calculator for type " + ticket.getTicketType()));
    }
}
