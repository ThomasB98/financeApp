package com.example.demo.Dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    @NotBlank(message = "Card name is required")
    @Size(min = 2, max = 50, message = "Card name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Valid till date is required")
    @FutureOrPresent(message = "Valid till date must be today or in the future")
    private LocalDate validTill;

    @NotNull(message = "Card type is required")
    private String cardType;
}
