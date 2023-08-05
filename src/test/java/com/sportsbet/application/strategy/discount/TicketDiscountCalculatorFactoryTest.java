package com.sportsbet.application.strategy.discount;

import com.sportsbet.BaseTest;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import com.sportsbet.interfaces.dto.CustomerDTO;
import com.sportsbet.interfaces.dto.TicketDTO;
import com.sportsbet.interfaces.dto.TransactionRequest;
import com.sportsbet.interfaces.dto.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class TicketDiscountCalculatorFactoryTest extends BaseTest {

    @Mock
    private NoDiscountCalculator noDiscountCalculator;
    @Mock
    private SeniorDiscountCalculator seniorDiscountCalculator;
    @Mock
    private ChildrenDiscountCalculator childrenDiscountCalculator;
    @InjectMocks
    private TicketDiscountCalculatorFactory ticketDiscountCalculatorFactory;

    @ParameterizedTest
    @MethodSource("parameters")
    public void testGetCalculator(Ticket ticket, Class clazz) {
        var actualDiscountCalculator = ticketDiscountCalculatorFactory.getPriceCalculator(ticket);

        assertThat(actualDiscountCalculator, instanceOf(clazz));
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Ticket.builder().ticketType(TicketType.ADULT).quantity(generateRandomInt(1, Integer.MAX_VALUE)).build(), NoDiscountCalculator.class),
                Arguments.of(Ticket.builder().ticketType(TicketType.TEEN).quantity(generateRandomInt(1, Integer.MAX_VALUE)).build(), NoDiscountCalculator.class),
                Arguments.of(Ticket.builder().ticketType(TicketType.CHILDREN).quantity(generateRandomInt(1, 3)).build(), NoDiscountCalculator.class),
                Arguments.of(Ticket.builder().ticketType(TicketType.SENIOR).quantity(generateRandomInt(1, Integer.MAX_VALUE)).build(), SeniorDiscountCalculator.class),
                Arguments.of(Ticket.builder().ticketType(TicketType.CHILDREN).quantity(generateRandomInt(3, Integer.MAX_VALUE)).build(), ChildrenDiscountCalculator.class)
        );
    }

}
