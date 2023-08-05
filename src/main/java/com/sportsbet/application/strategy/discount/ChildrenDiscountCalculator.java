package com.sportsbet.application.strategy.discount;

import com.sportsbet.domain.Ticket;
import org.springframework.stereotype.Component;

/**
 * Implementation of the TicketDiscountCalculator interface for calculating discount rate for children ticket with 3
 * tickets.
 */
@Component
public class ChildrenDiscountCalculator implements TicketDiscountCalculator {

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateDiscountRate(Ticket ticket) {
        return 0.75;
    }
}
