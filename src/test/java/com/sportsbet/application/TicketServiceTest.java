package com.sportsbet.application;

import com.sportsbet.BaseTest;
import com.sportsbet.application.strategy.discount.TestDiscountCalculator;
import com.sportsbet.application.strategy.discount.TicketDiscountCalculatorFactory;
import com.sportsbet.application.strategy.price.TestTicketPriceCalculator;
import com.sportsbet.application.strategy.price.TicketPriceCalculatorFactory;
import com.sportsbet.domain.Customer;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TicketServiceTest extends BaseTest {

    @Mock
    private TicketPriceCalculatorFactory ticketPriceCalculatorFactory;

    @Mock
    private TicketDiscountCalculatorFactory ticketDiscountCalculatorFactory;

    @Mock
    private TestTicketPriceCalculator priceCalculator;

    @Mock
    private TestDiscountCalculator discountCalculator;

    @InjectMocks
    private TicketService ticketService;

    @ParameterizedTest
    @MethodSource("determineTicketTypeByConsumerDetailsParams")
    public void testDetermineTicketTypeByConsumerDetails(Customer customer, TicketType expectedTickType) {
        //when
        var actualTicketType = ticketService.determineTicketTypeByConsumerDetails(customer);

        //should
        assertEquals(expectedTickType, actualTicketType);
    }

    private static Stream<Arguments> determineTicketTypeByConsumerDetailsParams() {
        return Stream.of(
            Arguments.of(
                Customer.builder().name(randomString()).age(18).build(),
                TicketType.ADULT
            ),
            Arguments.of(
                Customer.builder().name(randomString()).age(generateRandomInt(18, 65)).build(),
                TicketType.ADULT
            ),
            Arguments.of(
                Customer.builder().name(randomString()).age(64).build(),
                TicketType.ADULT
            ),
            Arguments.of(
                Customer.builder().name(randomString()).age(65).build(),
                TicketType.SENIOR
            ),
            Arguments.of(
                Customer.builder().name(randomString()).age(generateRandomInt(66, 110)).build(),
                TicketType.SENIOR
            ),
            Arguments.of(
                Customer.builder().name(randomString()).age(11).build(),
                TicketType.TEEN
            ),
            Arguments.of(
                Customer.builder().name(randomString()).age(17).build(),
                TicketType.TEEN
            ),
            Arguments.of(
                Customer.builder().name(randomString()).age(generateRandomInt(12, 18)).build(),
                TicketType.TEEN
            ),
            Arguments.of(
                Customer.builder().name(randomString()).age(10).build(),
                TicketType.CHILDREN
            ),
            Arguments.of(
                Customer.builder().name(randomString()).age(generateRandomInt(0, 10)).build(),
                TicketType.CHILDREN
            )
        );
    }

    @Test
    void testCreateTicket() {
        //given
        var quantity = 1;
        var ticketPrice = 25.00;
        var ticketType = TicketType.ADULT;
        var discountRate = 0.7;

        //when
        when(ticketPriceCalculatorFactory.getPriceCalculator(any(Ticket.class))).thenReturn(
            priceCalculator);
        when(ticketDiscountCalculatorFactory.getPriceCalculator(any(Ticket.class))).thenReturn(
            discountCalculator);
        when(priceCalculator.calculatePrice(any(Ticket.class))).thenReturn(
            Ticket.builder().ticketType(ticketType).totalCost(ticketPrice * quantity).quantity(quantity)
                .build());
        when(discountCalculator.calculateDiscountRate(any(Ticket.class))).thenReturn(discountRate);
        var actualTicket = ticketService.createTicket(ticketType, quantity);

        //should
        var expectedTotalCost = ticketPrice * quantity * discountRate;
        var expectedTicket = Ticket.builder().ticketType(TicketType.ADULT).quantity(quantity)
            .totalCost(expectedTotalCost).build();
        assertEquals(expectedTicket, actualTicket);
    }
}
