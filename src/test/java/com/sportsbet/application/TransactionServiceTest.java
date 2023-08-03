package com.sportsbet.application;

import com.sportsbet.domain.Customer;
import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import com.sportsbet.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Spy
    private TicketService ticketService;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void shouldGet1SeniorTickets() {
        //given
        var transactionId = 1L;
        var customerList = new ArrayList<Customer>();
        customerList.add(Customer.builder().name("Billy Kidd").age(70).build());
        var transaction = Transaction.builder().transactionId(transactionId).customers(customerList).build();

        //when
        var actualTransaction = transactionService.purchaseTickets(transaction);

        //should
        var tickets = new ArrayList<Ticket>();
        tickets.add(Ticket.builder().ticketType(TicketType.SENIOR).quantity(1).totalCost(17.50).build());
        var transactionTotalCost = 17.50;
        var expectedTransaction = Transaction.builder().transactionId(transactionId).customers(customerList).tickets(tickets).totalCost(transactionTotalCost).build();

        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    void shouldGet1AdultTickets() {
        //given
        var transactionId = 1L;
        var customerList = new ArrayList<Customer>();
        customerList.add(Customer.builder().name("Billy Kidd").age(36).build());
        var transaction = Transaction.builder().transactionId(transactionId).customers(customerList).build();

        //when
        var actualTransaction = transactionService.purchaseTickets(transaction);

        //should
        var tickets = new ArrayList<Ticket>();
        tickets.add(Ticket.builder().ticketType(TicketType.ADULT).quantity(1).totalCost(25.00).build());
        var transactionTotalCost = 25.00;
        var expectedTransaction = Transaction.builder().transactionId(transactionId).customers(customerList).tickets(tickets).totalCost(transactionTotalCost).build();

        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    void shouldGet1ChildrenTickets() {
        //given
        var transactionId = 1L;
        var customerList = new ArrayList<Customer>();
        customerList.add(Customer.builder().name("Jane Doe").age(5).build());
        var transaction = Transaction.builder().transactionId(transactionId).customers(customerList).build();

        //when
        var actualTransaction = transactionService.purchaseTickets(transaction);

        //should
        var tickets = new ArrayList<Ticket>();
        tickets.add(Ticket.builder().ticketType(TicketType.CHILDREN).quantity(1).totalCost(5.00).build());
        var transactionTotalCost = 5.00;
        var expectedTransaction = Transaction.builder().transactionId(transactionId).customers(customerList).tickets(tickets).totalCost(transactionTotalCost).build();

        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    void shouldGet2ChildrenTicketsAnd1SeniorTicket() {
        //given
        var transactionId = 1L;
        var customerList = new ArrayList<Customer>();
        customerList.add(Customer.builder().name("John Smith").age(70).build());
        customerList.add(Customer.builder().name("Jane Doe").age(5).build());
        customerList.add(Customer.builder().name("Bob Doe").age(6).build());
        var transaction = Transaction.builder().transactionId(transactionId).customers(customerList).build();

        //when
        var actualTransaction = transactionService.purchaseTickets(transaction);

        //should
        var tickets = new ArrayList<Ticket>();
        tickets.add(Ticket.builder().ticketType(TicketType.CHILDREN).quantity(2).totalCost(10.00).build());
        tickets.add(Ticket.builder().ticketType(TicketType.SENIOR).quantity(1).totalCost(17.50).build());
        var transactionTotalCost = 27.50;
        var expectedTransaction = Transaction.builder().transactionId(transactionId).customers(customerList).tickets(tickets).totalCost(transactionTotalCost).build();

        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    void shouldGet3ChildrenTicketsAnd1AdultTicketAnd1TeenTicket() {
        //given
        var transactionId = 2L;
        var customerList = new ArrayList<Customer>();
        customerList.add(Customer.builder().name("Billy Kidd").age(36).build());
        customerList.add(Customer.builder().name("Zoe Daniels").age(3).build());
        customerList.add(Customer.builder().name("George White").age(8).build());
        customerList.add(Customer.builder().name("Tommy Anderson").age(9).build());
        customerList.add(Customer.builder().name("Joe Smith").age(17).build());
        var transaction = Transaction.builder().transactionId(transactionId).customers(customerList).build();

        //when
        var actualTransaction = transactionService.purchaseTickets(transaction);

        //should
        var tickets = new ArrayList<Ticket>();
        tickets.add(Ticket.builder().ticketType(TicketType.ADULT).quantity(1).totalCost(25.00).build());
        tickets.add(Ticket.builder().ticketType(TicketType.CHILDREN).quantity(3).totalCost(11.25).build());
        tickets.add(Ticket.builder().ticketType(TicketType.TEEN).quantity(1).totalCost(12).build());
        var transactionTotalCost = 48.25;
        var expectedTransaction = Transaction.builder().transactionId(transactionId).customers(customerList).tickets(tickets).totalCost(transactionTotalCost).build();

        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    void shouldGet1AdultTicketAnd1ChildrenTicketAnd1SeniorTicketAnd1TeenTicket() {
        //given
        var transactionId = 3L;
        var customerList = new ArrayList<Customer>();
        customerList.add(Customer.builder().name("Jesse James").age(36).build());
        customerList.add(Customer.builder().name("Daniel Anderson").age(95).build());
        customerList.add(Customer.builder().name("Mary Jones").age(15).build());
        customerList.add(Customer.builder().name("Michelle Parker").age(10).build());
        var transaction = Transaction.builder().transactionId(transactionId).customers(customerList).build();

        //when
        var actualTransaction = transactionService.purchaseTickets(transaction);

        //should
        var tickets = new ArrayList<Ticket>();
        tickets.add(Ticket.builder().ticketType(TicketType.ADULT).quantity(1).totalCost(25.00).build());
        tickets.add(Ticket.builder().ticketType(TicketType.CHILDREN).quantity(1).totalCost(5.00).build());
        tickets.add(Ticket.builder().ticketType(TicketType.SENIOR).quantity(1).totalCost(17.50).build());
        tickets.add(Ticket.builder().ticketType(TicketType.TEEN).quantity(1).totalCost(12.00).build());
        var transactionTotalCost = 59.50;
        var expectedTransaction = Transaction.builder().transactionId(transactionId).customers(customerList).tickets(tickets).totalCost(transactionTotalCost).build();

        assertEquals(expectedTransaction, actualTransaction);
    }

}
