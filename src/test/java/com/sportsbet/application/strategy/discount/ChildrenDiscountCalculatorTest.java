package com.sportsbet.application.strategy.discount;

import com.sportsbet.BaseTest;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildrenDiscountCalculatorTest extends BaseTest {

    @InjectMocks
    private ChildrenDiscountCalculator discountCalculator;

    @Test
    void shouldGetSeniorDiscountCalculator() {
        //given
        var quantity = generateRandomInt(1, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.CHILDREN).quantity(quantity).build();

        //when
        var actualDiscountRate = discountCalculator.calculateDiscountRate(ticket);

        //should
        var expectedDiscountRate = 0.75;

        assertEquals(expectedDiscountRate, actualDiscountRate);
    }

}