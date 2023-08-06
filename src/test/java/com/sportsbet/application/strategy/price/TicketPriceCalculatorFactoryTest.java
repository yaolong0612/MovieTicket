package com.sportsbet.application.strategy.price;

import com.sportsbet.BaseTest;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import com.sportsbet.infrastructure.error.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TicketPriceCalculatorFactoryTest extends BaseTest {

    @InjectMocks
    private TicketPriceCalculatorFactory factory;
    @Mock
    private TestTicketPriceCalculator ticketPriceCalculator;

    @BeforeEach
    public void setUp() {
        var calculators = new ArrayList<TicketPriceCalculator>();
        when(ticketPriceCalculator.getTicketType()).thenReturn(TicketType.ADULT);
        calculators.add(ticketPriceCalculator);
        factory = new TicketPriceCalculatorFactory(calculators);
    }

    @Test
    void testGetCalculator() {
        var adultTicket = Ticket.builder().ticketType(TicketType.ADULT).quantity(1).build();
        TicketPriceCalculator adultCalculator = factory.getPriceCalculator(adultTicket);
        assertNotNull(adultCalculator);
        assertEquals(TicketType.ADULT, adultCalculator.getTicketType());

        var teenTicket = Ticket.builder().ticketType(TicketType.TEEN).quantity(1).build();
        assertThrows(ServiceException.class, () -> factory.getPriceCalculator(teenTicket));
    }
}
