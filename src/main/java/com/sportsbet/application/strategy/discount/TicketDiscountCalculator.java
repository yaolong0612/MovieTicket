package com.sportsbet.application.strategy.discount;

import com.sportsbet.domain.Ticket;

/**
 * The TicketDiscountCalculator interface represents a strategy for calculating the discount rate base on ticket information.
 */
public interface TicketDiscountCalculator {

    /**
     * Calculates the discount rate for the provided ticket.
     *
     * @param ticket The ticket for which to calculate the discount rate.
     * @return The discount rate as a decimal value between 0 and 1. For example, a discount rate of 0.1 represents 10% off.
     */
    double calculateDiscountRate(Ticket ticket);
}
