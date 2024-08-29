package com.example.customer_account_management.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerDTO {
    private String name;
    private String phone;
    private String email;
    private BigDecimal balance;
}
