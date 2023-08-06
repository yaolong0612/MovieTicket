package com.sportsbet.application.strategy.discount;

import com.sportsbet.domain.Ticket;

/**
 * Implementation of the TicketDiscountCalculator interface for only testing purpose
 */
public class TestDiscountCalculator implements TicketDiscountCalculator {

    @Override
    public double calculateDiscountRate(Ticket ticket) {
        return 1.00;
    }
}
