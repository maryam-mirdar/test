package com.example.customer_account_management.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "transaction_history")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "deposit")
    private BigDecimal deposit;

    @Column(name = "withdrawal")
    private BigDecimal withdrawal;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
}
