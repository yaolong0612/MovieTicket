package com.sportsbet.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String name;
    private int age;

}
