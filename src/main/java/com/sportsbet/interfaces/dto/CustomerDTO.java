package com.sportsbet.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "Customer information")
public class CustomerDTO {

    @Schema(description = "Customer name")
    private String name;
    @Range(min = 0, max = 120, message = "Age should be from 0 to 120")
    @NotNull(message = "Please provide customer age")
    @Schema(description = "Customer age", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer age;
}
