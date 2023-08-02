package com.sportsbet.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Transaction {

    private Long transactionId;
    private List<Customer> customers;
    private List<Ticket> tickets;
    private double totalCost;
}
