package com.example.customer_account_management.service;

import com.example.customer_account_management.model.dto.CustomerDTO;
import com.example.customer_account_management.model.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Customer save(CustomerDTO customerDTO);

    Customer update(long id, CustomerDTO updatedCustomerDTO);

    void delete(long id);

    Page<Customer> fetchCustomerList(String name, String phone, int pageNo, int pageSize);
}
