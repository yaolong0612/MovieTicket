package com.sportsbet.application.strategy.discount;

import com.sportsbet.domain.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implementation of the TicketDiscountCalculator interface for calculating discount rate for senior tickets.
 */
@Component
public class SeniorDiscountCalculator implements TicketDiscountCalculator {

    @Value("${application.ticket.discount.senior}")
    private double discountRate;

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateDiscountRate(Ticket ticket) {
        return discountRate;
    }
}
