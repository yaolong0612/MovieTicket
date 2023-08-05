package com.sportsbet.application.strategy.price;

import com.sportsbet.BaseTest;
import com.sportsbet.application.strategy.price.AdultTicketPriceCalculator;
import com.sportsbet.application.strategy.price.ChildrenTicketPriceCalculator;
import com.sportsbet.application.strategy.price.SeniorTicketPriceCalculator;
import com.sportsbet.application.strategy.price.TicketPriceCalculator;
import com.sportsbet.application.strategy.price.TicketPriceCalculatorFactory;
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
    private TicketPriceCalculator adultTicketPriceCalculator;
    @Mock
    private TicketPriceCalculator seniorTicketPriceCalculator;
    @Mock
    private TicketPriceCalculator childrenTicketPriceCalculator;

    @BeforeEach
    public void setUp() {
        var calculators = new ArrayList<TicketPriceCalculator>();

        when(adultTicketPriceCalculator.getTicketType()).thenReturn(TicketType.ADULT);
        when(childrenTicketPriceCalculator.getTicketType()).thenReturn(TicketType.CHILDREN);
        when(seniorTicketPriceCalculator.getTicketType()).thenReturn(TicketType.SENIOR);

        calculators.add(adultTicketPriceCalculator);
        calculators.add(seniorTicketPriceCalculator);
        calculators.add(childrenTicketPriceCalculator);

        factory = new TicketPriceCalculatorFactory(calculators);
    }


    @Test
    void testGetCalculator() {
        var adultTicket = Ticket.builder().ticketType(TicketType.ADULT).quantity(1).build();
        TicketPriceCalculator adultCalculator = factory.getPriceCalculator(adultTicket);
        assertNotNull(adultCalculator);
        assertEquals(TicketType.ADULT, adultCalculator.getTicketType());

        var childrenTicket = Ticket.builder().ticketType(TicketType.CHILDREN).quantity(1).build();
        TicketPriceCalculator childrenCalculator = factory.getPriceCalculator(childrenTicket);
        assertNotNull(childrenCalculator);
        assertEquals(TicketType.CHILDREN, childrenCalculator.getTicketType());

        var seniorTicket = Ticket.builder().ticketType(TicketType.SENIOR).quantity(1).build();
        TicketPriceCalculator seniorCalculator = factory.getPriceCalculator(seniorTicket);
        assertNotNull(seniorCalculator);
        assertEquals(TicketType.SENIOR, seniorCalculator.getTicketType());

        var teenTicket = Ticket.builder().ticketType(TicketType.TEEN).quantity(1).build();
        assertThrows(ServiceException.class, () -> factory.getPriceCalculator(teenTicket));
    }
}
