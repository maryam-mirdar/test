package com.example.customer_account_management.service.impl;

import com.example.customer_account_management.model.entity.Customer;
import com.example.customer_account_management.model.entity.TransactionHistory;
import com.example.customer_account_management.model.dto.TransactionDepositDTO;
import com.example.customer_account_management.model.dto.TransactionWithdrawalDTO;
import com.example.customer_account_management.exception.NoSuchEntityExistsException;
import com.example.customer_account_management.repository.CustomerRepository;
import com.example.customer_account_management.repository.TransactionRepository;
import com.example.customer_account_management.service.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionServiceImpl implements TransactionService {
    final TransactionRepository transactionRepository;
    final CustomerRepository customerRepository;

    @Override
    public TransactionHistory registerDeposit(TransactionDepositDTO transactionDepositDTO) {
        Customer customer = customerRepository.findById(transactionDepositDTO.getCustomerId()).orElseThrow(() -> new NoSuchEntityExistsException("customer with id=" + transactionDepositDTO.getCustomerId() + " does not exist."));
        customer.setBalance(customer.getBalance().add(transactionDepositDTO.getDeposit()));
        customerRepository.save(customer);
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setDeposit(transactionDepositDTO.getDeposit());
        transactionHistory.setWithdrawal(BigDecimal.ZERO);
        transactionHistory.setDate(LocalDate.now());
        transactionHistory.setCustomer(customer);
        return transactionRepository.save(transactionHistory);
    }

    @Override
    public TransactionHistory registerWithdrawal(TransactionWithdrawalDTO transactionWithdrawalDTO) {
        Customer customer = customerRepository.findById(transactionWithdrawalDTO.getCustomerId()).orElseThrow(() -> new NoSuchEntityExistsException("customer with id=" + transactionWithdrawalDTO.getCustomerId() + " does not exist."));
        customer.setBalance(customer.getBalance().subtract(transactionWithdrawalDTO.getWithdrawal()));
        customerRepository.save(customer);
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setWithdrawal(transactionWithdrawalDTO.getWithdrawal());
        transactionHistory.setDeposit(BigDecimal.ZERO);
        transactionHistory.setDate(LocalDate.now());
        transactionHistory.setCustomer(customer);
        return transactionRepository.save(transactionHistory);
    }

    @Override
    public Page<TransactionHistory> getTransactionHistoryListByCustomerId(long customerId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<TransactionHistory> transactionHistories = transactionRepository.findAllByCustomer_Id(customerId, pageable);
        return transactionHistories;
    }
}
