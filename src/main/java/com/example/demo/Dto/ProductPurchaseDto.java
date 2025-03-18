package com.example.demo.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseDto {

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @NotNull(message = "Product ID is required")
    @Positive(message = "Product ID must be a positive number")
    private Long productId;

    @NotNull(message = "Tenure is required")
    @Pattern(regexp = "^(3|6|9|12)$", message = "Tenure must be 3, 6, 9, or 12 months")
    private int tenureMonths;
}
