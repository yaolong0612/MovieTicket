package com.sportsbet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a Transaction entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    private Long transactionId;
    private List<Customer> customers;
    private List<Ticket> tickets;
    private double totalCost;
}
