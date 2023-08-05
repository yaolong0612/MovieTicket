package com.sportsbet.application;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import com.sportsbet.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Application service for handling transaction operations.
 */
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TicketService ticketService;

    /**
     * Process the purchase of movie tickets for the given transaction and calculate the total cost.
     * <p>
     * This method takes a Transaction object containing customer information and processes the purchase
     * of movie tickets for each customer. It calculates the total cost of the transaction and returns
     * a new Transaction object with the purchased movie tickets and the total cost.
     *
     * @param transaction The Transaction object representing the movie ticket purchase transaction.
     * @return A new Transaction object with the purchased movie tickets and the total cost.
     */
    public Transaction purchaseTickets(Transaction transaction) {
        // Key is ticket type, value is quantity is this type of ticket in this transaction
        var ticketTypeCounts = transaction.getCustomers().stream()
                .collect(Collectors.groupingBy(customer -> ticketService.determineTicketTypeByConsumerDetails(customer),
                        Collectors.summingInt(customer -> 1)));

        var tickets = new ArrayList<Ticket>();
        var transactionTotalCost = 0.0;

        for (var ticketCountEntry : ticketTypeCounts.entrySet()) {
            var ticketType = ticketCountEntry.getKey();
            var ticketQuantity = ticketCountEntry.getValue();

            var ticket = Ticket.builder().ticketType(ticketType).quantity(ticketQuantity).build();
            var ticketTotalCost = ticketService.calculateTicketTotalCost(ticket);
            ticket.setTotalCost(ticketTotalCost);

            tickets.add(ticket);

            transactionTotalCost += ticketTotalCost;
        }

        tickets.sort(Comparator.comparing(ticket -> ticket.getTicketType().toString()));

        var transactionWithTickets = Transaction.builder()
                .transactionId(transaction.getTransactionId())
                .customers(transaction.getCustomers())
                .tickets(tickets)
                .totalCost(transactionTotalCost)
                .build();

        return transactionWithTickets;
    }
}
