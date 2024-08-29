package com.example.customer_account_management.service;

import com.example.customer_account_management.model.dto.TransactionDepositDTO;
import com.example.customer_account_management.model.dto.TransactionWithdrawalDTO;
import com.example.customer_account_management.model.entity.TransactionHistory;
import org.springframework.data.domain.Page;

public interface TransactionService {
    TransactionHistory registerDeposit(TransactionDepositDTO transactionDepositDTO);

    TransactionHistory registerWithdrawal(TransactionWithdrawalDTO transactionWithdrawalDTO);

    Page<TransactionHistory> getTransactionHistoryListByCustomerId(long customerId, int pageNo, int pageSize);
}
