package com.example.customer_account_management.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionWithdrawalDTO {
    private BigDecimal withdrawal;
    private LocalDate date;
    private Long customerId;
}
