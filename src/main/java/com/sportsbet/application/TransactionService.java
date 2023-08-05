package com.sportsbet.application;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
     * This method takes a Transaction object containing customer information and processes the purchase of movie
     * tickets for each customer. It calculates the total cost of the transaction and returns a new Transaction object
     * with the purchased movie tickets and the total cost.
     *
     * @param transaction The Transaction object representing the movie ticket purchase transaction.
     * @return A new Transaction object with the purchased movie tickets and the total cost.
     */
    public Transaction purchaseTickets(Transaction transaction) {
        // Key is ticket type, value is quantity is this type of ticket in this transaction
        var ticketTypeCounts = transaction.getCustomers().stream()
            .collect(Collectors.groupingBy(customer -> ticketService.determineTicketTypeByConsumerDetails(customer),
                Collectors.summingInt(customer -> 1)));

        var tickets = ticketTypeCounts.entrySet().stream()
            .map(ticketCountEntry -> ticketService.createTicket(ticketCountEntry.getKey(), ticketCountEntry.getValue()))
            .sorted(Comparator.comparing(ticket -> ticket.getTicketType().toString()))
            .collect(Collectors.toList());

        var transactionWithTickets = Transaction.builder()
            .transactionId(transaction.getTransactionId())
            .customers(transaction.getCustomers())
            .tickets(tickets)
            .build();
        var transactionTotalCost = calculateTotalCost(transactionWithTickets);

        transactionWithTickets.setTotalCost(transactionTotalCost);

        return transactionWithTickets;
    }

    /**
     * Calculates the total cost of a transaction by summing up the total costs of all the tickets in the transaction.
     *
     * @param transaction The transaction for which the total cost is to be calculated.
     * @return The total cost of the transaction as a double value.
     */
    private double calculateTotalCost(Transaction transaction) {
        var transactionTotalCost = 0.0;

        for (Ticket ticket : transaction.getTickets()) {
            transactionTotalCost += ticket.getTotalCost();
        }

        return transactionTotalCost;
    }

}
