package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "emi_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EMITransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private TenureMonths tenure;

    private double monthlyInstallment;

    private LocalDate startDate;

    private LocalDate endDate;

    @ElementCollection
    private List<LocalDate> paymentDueDates;

    private boolean isPaid = false;
}
