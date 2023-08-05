package com.sportsbet.application.strategy.discount;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketDiscountCalculatorFactory {

    private final ChildrenDiscountCalculator childrenDiscountCalculator;
    private final SeniorDiscountCalculator seniorDiscountCalculator;
    private final NoDiscountCalculator noDiscountCalculator;

    public TicketDiscountCalculator getPriceCalculator(Ticket ticket) {
        if (ticket.getTicketType() == TicketType.SENIOR) {
            return seniorDiscountCalculator;
        }
        if (ticket.getTicketType() == TicketType.CHILDREN && ticket.getQuantity() >= 3) {
            return childrenDiscountCalculator;
        }
        return noDiscountCalculator;
    }
}
