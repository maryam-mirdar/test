package com.example.customer_account_management.controller;

import com.example.customer_account_management.model.dto.TransactionDepositDTO;
import com.example.customer_account_management.model.dto.TransactionWithdrawalDTO;
import com.example.customer_account_management.model.entity.TransactionHistory;
import com.example.customer_account_management.service.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("/transaction")
public class TransactionController {
    final TransactionService transactionService;

    @PostMapping("/deposit")
    public TransactionHistory registerDeposit(@RequestBody TransactionDepositDTO transactionDepositDTO) {
        return transactionService.registerDeposit(transactionDepositDTO);
    }

    @PostMapping("/withdrawal")
    public TransactionHistory registerWithdrawal(@RequestBody TransactionWithdrawalDTO transactionWithdrawalDTO) {
        return transactionService.registerWithdrawal(transactionWithdrawalDTO);
    }

    @GetMapping("/list/{customerId}")
    public ResponseEntity<Page<TransactionHistory>> getTransactionHistoryList(@PathVariable long customerId,
                                                                              @RequestParam(defaultValue = "0") int pageNo,
                                                                              @RequestParam(defaultValue = "5") int pageSize) {
        Page<TransactionHistory> transactionHistories = transactionService.getTransactionHistoryListByCustomerId(customerId, pageNo, pageSize);
        return ResponseEntity.ok(transactionHistories);
    }
}
