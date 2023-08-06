package com.sportsbet.application.strategy.price;

import com.sportsbet.BaseTest;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import com.sportsbet.infrastructure.error.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TeenTicketPriceCalculatorTest extends BaseTest {

    @Autowired
    private TeenTicketPriceCalculator ticketPriceCalculator;
    @Value("${application.ticket.price.teen}")
    private double price;

    @Test
    void shouldGetAdultTicket() {
        //given
        var quantity = generateRandomInt(1, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.TEEN).quantity(quantity).build();

        //when
        double actualTotalCost = ticketPriceCalculator.calculatePrice(ticket).getTotalCost();

        //should
        double expectedActualTotalCost = price * quantity;

        assertEquals(expectedActualTotalCost, actualTotalCost);
    }

    @Test
    void shouldGetServiceException() {
        //given
        var quantity = generateRandomInt(1, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.CHILDREN).quantity(quantity).build();

        assertThrows(ServiceException.class, () -> ticketPriceCalculator.calculatePrice(ticket));
    }
}

