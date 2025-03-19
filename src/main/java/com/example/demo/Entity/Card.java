package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="Cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Card name is required")
    @Size(min = 2, max = 50, message = "Card name must be between 2 and 50 characters")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Valid till date is required")
    @FutureOrPresent(message = "Valid till date must be today or in the future")
    private LocalDate validTill;

    @NotNull(message = "Card type is required")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private Boolean isActivated=false;

    @OneToOne(mappedBy = "card")
    private User user;

    private int remainingLimit;
}
