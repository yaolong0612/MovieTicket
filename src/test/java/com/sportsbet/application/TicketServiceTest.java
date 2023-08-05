package com.sportsbet.application;

import com.sportsbet.BaseTest;
import com.sportsbet.domain.Customer;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketServiceTest extends BaseTest {

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
    void testCalculateTicketTotalCostFor1AdultTicket() {
        //given
        int quantity = 1;
        var ticket = Ticket.builder().ticketType(TicketType.ADULT).quantity(quantity).build();

        //when
        var actualTotalCost = ticketService.calculateTicketTotalCost(ticket);

        //should
        var expectedTotalCost = 25.00 * quantity;
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    void testCalculateTicketTotalCostForMoreThan1AdultTicket() {
        //given
        int quantity = generateRandomInt(2, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.ADULT).quantity(quantity).build();

        //when
        var actualTotalCost = ticketService.calculateTicketTotalCost(ticket);

        //should
        var expectedTotalCost = 25.00 * quantity;
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    void testCalculateTicketTotalCostFor1SeniorTicket() {
        //given
        int quantity = 1;
        var ticket = Ticket.builder().ticketType(TicketType.SENIOR).quantity(quantity).build();

        //when
        var actualTotalCost = ticketService.calculateTicketTotalCost(ticket);

        //should
        var expectedTotalCost = 25.00 * 0.7 * quantity;
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    void testCalculateTicketTotalCostForMoreThan1SeniorTicket() {
        //given
        int quantity = generateRandomInt(2, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.SENIOR).quantity(quantity).build();

        //when
        var actualTotalCost = ticketService.calculateTicketTotalCost(ticket);

        //should
        var expectedTotalCost = 25.00 * 0.7 * quantity;
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    void testCalculateTicketTotalCostFor1TeenTicket() {
        //given
        int quantity = 1;
        var ticket = Ticket.builder().ticketType(TicketType.TEEN).quantity(quantity).build();

        //when
        var actualTotalCost = ticketService.calculateTicketTotalCost(ticket);

        //should
        var expectedTotalCost = 12.00 * quantity;
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    void testCalculateTicketTotalCostForMoreThan1TeenTicket() {
        //given
        int quantity = generateRandomInt(2, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.TEEN).quantity(quantity).build();

        //when
        var actualTotalCost = ticketService.calculateTicketTotalCost(ticket);

        //should
        var expectedTotalCost = 12.00 * quantity;
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    void testCalculateTicketTotalCostForLessThan3ChildrenTicket() {
        //given
        int quantity = generateRandomInt(1, 3);
        var ticket = Ticket.builder().ticketType(TicketType.CHILDREN).quantity(quantity).build();

        //when
        var actualTotalCost = ticketService.calculateTicketTotalCost(ticket);

        //should
        var expectedTotalCost = 5.00 * quantity;
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    void testCalculateTicketTotalCostFor3OrMoreChildrenTicket() {
        //given
        int quantity = generateRandomInt(3, Integer.MAX_VALUE);
        var ticket = Ticket.builder().ticketType(TicketType.CHILDREN).quantity(quantity).build();

        //when
        var actualTotalCost = ticketService.calculateTicketTotalCost(ticket);

        //should
        var expectedTotalCost = 5.00 * quantity * 0.75;
        assertEquals(expectedTotalCost, actualTotalCost);
    }
}
