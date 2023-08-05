package com.sportsbet.application.strategy.discount;

import com.sportsbet.BaseTest;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoDiscountCalculatorTest extends BaseTest {

    @InjectMocks
    private NoDiscountCalculator discountCalculator;

    @Test
    void shouldGetSeniorDiscountCalculator() {
        //given
        var quantity = generateRandomInt(1, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.ADULT).quantity(quantity).build();

        //when
        var actualDiscountRate = discountCalculator.calculateDiscountRate(ticket);

        //should
        var expectedDiscountRate = 1.00;

        assertEquals(expectedDiscountRate, actualDiscountRate);
    }

}