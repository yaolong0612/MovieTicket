package com.sportsbet.application;

import com.sportsbet.BaseTest;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketServiceTest extends BaseTest {

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testDetermineAdultTicketTypeForAge18() {
        //given
        var age = 18;

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.ADULT, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAgeGreaterThen18ButLessThan65() {
        //given
        var age = generateRandomInt(18, 65);

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.ADULT, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge64() {
        //given
        var age = 64;

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.ADULT, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge65() {
        //given
        var age = 65;

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.SENIOR, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAgeGreaterThan65() {
        //given
        var age = generateRandomInt(66, 110);

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.SENIOR, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge11() {
        //given
        var age = 11;

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.TEEN, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge17() {
        //given
        var age = 17;

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.TEEN, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAgeGreaterThan11ButLessThan18() {
        //given
        var age = generateRandomInt(12, 18);

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.TEEN, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge10() {
        //given
        var age = 10;

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.CHILDREN, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAgeLessThan10() {
        //given
        var age = generateRandomInt(0, 10);

        //when
        var actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.CHILDREN, actualTicketType);
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
