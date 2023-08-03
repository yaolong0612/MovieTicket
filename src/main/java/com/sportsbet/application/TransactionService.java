package com.sportsbet.application;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import com.sportsbet.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


/**
 * Application service for handling transaction operations.
 */
@Service
public class TransactionService {

    /**
     * Process the purchase of movie tickets for the given transaction and calculate the total cost.
     *
     * This method takes a Transaction object containing customer information and processes the purchase
     * of movie tickets for each customer. It calculates the total cost of the transaction and returns
     * a new Transaction object with the purchased movie tickets and the total cost.
     *
     * @param transaction The Transaction object representing the movie ticket purchase transaction.
     * @return A new Transaction object with the purchased movie tickets and the total cost.
     */
    public Transaction purchaseTickets(Transaction transaction) {
        var ticketTypeCounts = new HashMap<TicketType, Integer>();

        for(var customer:transaction.getCustomers()) {
            var ticketType = getTicketType(customer.getAge());
            int ticketCount = ticketTypeCounts.getOrDefault(ticketType, 0) + 1;
            ticketTypeCounts.put(ticketType, ticketCount);
        }

        var tickets = new ArrayList<Ticket>();
        var transactionTotalCost = 0.0;

        for(var ticketCountEntry : ticketTypeCounts.entrySet()) {
            var ticketType = ticketCountEntry.getKey();
            var ticketQuantity = ticketCountEntry.getValue();

            var ticket = Ticket.builder().ticketType(ticketType).quantity(ticketQuantity).build();
            var ticketTotalCost = calculateTicketTotalCost(ticket);
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

    /**
     * Calculates the total cost for a given ticket based on its ticket type and quantity.
     *
     * @param ticket The Ticket object for which the total cost needs to be calculated.
     * @return The total cost of the tickets of the given type and quantity.
     */
    private double calculateTicketTotalCost(Ticket ticket) {
        var costPerTicket = 0.0;
        switch (ticket.getTicketType()) {
            case ADULT:
                costPerTicket = 25.00;
                break;
            case SENIOR:
                costPerTicket = 25.00 * 0.70; // 30% cheaper
                break;
            case TEEN:
                costPerTicket = 12.00;
                break;
            case CHILDREN:
                costPerTicket = 5.00;
                if (ticket.getQuantity() >= 3) {
                    costPerTicket *= 0.75; // 25% discount
                }
                break;
            default:
                costPerTicket = 0.0;
        }
        return ticket.getQuantity() *  costPerTicket;
    }

    /**
     * Determines the ticket type based on the given age.
     *
     * @param age The age of the customer.
     * @return The ticket type corresponding to the given age.
     */
    private TicketType getTicketType(int age) {
        if (age < 11) {
            return TicketType.CHILDREN;
        } else if (age < 18) {
            return TicketType.TEEN;
        } else if (age < 65) {
            return TicketType.ADULT;
        } else {
            return TicketType.SENIOR;
        }
    }

}
