package com.sportsbet.application;

import com.sportsbet.BaseTest;
import com.sportsbet.domain.Customer;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import com.sportsbet.domain.Transaction;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionServiceTest extends BaseTest {

    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private TicketService ticketService;

    @Test
    void testPurchaseTickets() {
        //given
        var transactionID = 1L;
        var childrenCustomer1 = Customer.builder().name(randomString()).age(generateRandomInt(0, 10)).build();
        var childrenCustomer2 = Customer.builder().name(randomString()).age(generateRandomInt(0, 10)).build();
        var teenCustomer = Customer.builder().name(randomString()).age(generateRandomInt(11, 17)).build();
        var adultCustomer = Customer.builder().name(randomString()).age(generateRandomInt(18, 65)).build();
        var seniorCustomer = Customer.builder().name(randomString()).age(generateRandomInt(66, 120)).build();
        var customers = List.of(childrenCustomer1, childrenCustomer2, teenCustomer, adultCustomer, seniorCustomer);
        var transaction = Transaction.builder().transactionId(transactionID).customers(customers).build();

        //when
        when(ticketService.determineTicketTypeByConsumerDetails(childrenCustomer1)).thenReturn(TicketType.CHILDREN);
        when(ticketService.determineTicketTypeByConsumerDetails(childrenCustomer2)).thenReturn(TicketType.CHILDREN);
        when(ticketService.determineTicketTypeByConsumerDetails(teenCustomer)).thenReturn(TicketType.TEEN);
        when(ticketService.determineTicketTypeByConsumerDetails(adultCustomer)).thenReturn(TicketType.ADULT);
        when(ticketService.determineTicketTypeByConsumerDetails(seniorCustomer)).thenReturn(TicketType.SENIOR);
        when(ticketService.createTicket(TicketType.ADULT, 1)).thenReturn(
            Ticket.builder().quantity(1).ticketType(TicketType.ADULT).totalCost(25.00).build());
        when(ticketService.createTicket(TicketType.TEEN, 1)).thenReturn(
            Ticket.builder().quantity(1).ticketType(TicketType.TEEN).totalCost(12.00).build());
        when(ticketService.createTicket(TicketType.SENIOR, 1)).thenReturn(
            Ticket.builder().quantity(1).ticketType(TicketType.SENIOR).totalCost(17.50).build());
        when(ticketService.createTicket(TicketType.CHILDREN, 2)).thenReturn(
            Ticket.builder().quantity(2).ticketType(TicketType.CHILDREN).totalCost(10.00).build());

        //should
        var actualTransaction = transactionService.purchaseTickets(transaction);
        var totalCost = 64.50;
        var tickets = List.of(Ticket.builder().quantity(1).ticketType(TicketType.ADULT).totalCost(25.00).build(),
            Ticket.builder().quantity(2).ticketType(TicketType.CHILDREN).totalCost(10.00).build(),
            Ticket.builder().quantity(1).ticketType(TicketType.SENIOR).totalCost(17.50).build(),
            Ticket.builder().quantity(1).ticketType(TicketType.TEEN).totalCost(12.00).build()
        );
        var expectedTransaction = Transaction.builder().transactionId(transactionID).customers(customers)
            .totalCost(totalCost).tickets(tickets).build();

        assertEquals(expectedTransaction, actualTransaction);
    }
}
