package com.sportsbet.application.strategy;

import com.sportsbet.BaseTest;
import com.sportsbet.application.strategy.price.AdultTicketPriceCalculator;
import com.sportsbet.application.strategy.price.ChildrenTicketPriceCalculator;
import com.sportsbet.application.strategy.price.SeniorTicketPriceCalculator;
import com.sportsbet.application.strategy.price.TicketPriceCalculator;
import com.sportsbet.application.strategy.price.TicketPriceCalculatorFactory;
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
    private AdultTicketPriceCalculator adultTicketPriceCalculator;
    @Mock
    private SeniorTicketPriceCalculator seniorTicketPriceCalculator;
    @Mock
    private ChildrenTicketPriceCalculator childrenTicketPriceCalculator;


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
    public void testGetCalculator() {
        TicketPriceCalculator adultCalculator = factory.getCalculator(TicketType.ADULT);
        assertNotNull(adultCalculator);
        assertEquals(TicketType.ADULT, adultCalculator.getTicketType());

        TicketPriceCalculator childrenCalculator = factory.getCalculator(TicketType.CHILDREN);
        assertNotNull(childrenCalculator);
        assertEquals(TicketType.CHILDREN, childrenCalculator.getTicketType());

        TicketPriceCalculator seniorCalculator = factory.getCalculator(TicketType.SENIOR);
        assertNotNull(seniorCalculator);
        assertEquals(TicketType.SENIOR, seniorCalculator.getTicketType());

        assertThrows(ServiceException.class, () -> factory.getCalculator(TicketType.TEEN));
    }
}
