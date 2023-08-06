package com.sportsbet.application.strategy.discount;

import com.sportsbet.BaseTest;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SeniorDiscountCalculatorTest extends BaseTest {

    @Autowired
    private SeniorDiscountCalculator discountCalculator;
    @Value("${application.ticket.discount.senior}")
    private double expectedDiscountRate;

    @Test
    void shouldGetSeniorDiscountCalculator() {
        //given
        var quantity = generateRandomInt(1, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.ADULT).quantity(quantity).build();

        //when
        var actualDiscountRate = discountCalculator.calculateDiscountRate(ticket);

        //should
        assertEquals(expectedDiscountRate, actualDiscountRate);
    }

}
