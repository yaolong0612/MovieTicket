package com.sportsbet.application.strategy.discount;

import com.sportsbet.domain.Ticket;
import org.springframework.stereotype.Component;

/**
 * Implementation of the TicketDiscountCalculator interface for calculating discount rate for all other types of
 * ticket.
 */
@Component
public class NoDiscountCalculator implements TicketDiscountCalculator {

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateDiscountRate(Ticket ticket) {
        return 1.00;
    }
}
