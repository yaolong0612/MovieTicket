package com.sportsbet.interfaces.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * DTO representing a customer
 * <p>
 * This class is used to transfer customer data between different layers of the application,
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    private String name;
    @Range(min = 0, max = 120, message = "Age should be from 0 to 120")
    private int age;
}
