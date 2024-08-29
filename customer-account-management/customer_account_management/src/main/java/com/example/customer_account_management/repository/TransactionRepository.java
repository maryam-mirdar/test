package com.example.customer_account_management.repository;

import com.example.customer_account_management.model.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionHistory, Long> {
    Page<TransactionHistory> findAllByCustomer_Id(long customerId, Pageable pageable);
}
