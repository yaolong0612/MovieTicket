package com.sportsbet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a customer entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private String name;
    private int age;
}
