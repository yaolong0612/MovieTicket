package com.sportsbet.application.strategy.price;

import com.sportsbet.BaseTest;
import com.sportsbet.application.strategy.price.SeniorTicketPriceCalculator;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import com.sportsbet.infrastructure.error.ServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeniorTicketPriceCalculatorTest extends BaseTest {

    @InjectMocks
    private SeniorTicketPriceCalculator ticketPriceCalculator;

    @Test
    void shouldGetAdultTicket() {
        //given
        var quantity = generateRandomInt(1, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.SENIOR).quantity(quantity).build();

        //when
        double actualTotalCost = ticketPriceCalculator.calculatePrice(ticket).getTotalCost();

        //should
        double expectedActualTotalCost = 25.00 * quantity;

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
