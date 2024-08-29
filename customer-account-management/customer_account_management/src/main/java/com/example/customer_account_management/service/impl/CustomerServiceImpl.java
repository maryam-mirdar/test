package com.example.customer_account_management.service.impl;

import com.example.customer_account_management.model.entity.Customer;
import com.example.customer_account_management.model.dto.CustomerDTO;
import com.example.customer_account_management.exception.NoSuchEntityExistsException;
import com.example.customer_account_management.repository.CustomerRepository;
import com.example.customer_account_management.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {
    
    final CustomerRepository customerRepository;

    @Override
    public Customer save(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setBalance(customerDTO.getBalance());

        return customerRepository.save(customer);
    }

    @Override
    public Customer update(long id, CustomerDTO updatedCustomerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NoSuchEntityExistsException("customer with id=" + id + " does not exist."));

        customer.setName(Optional.ofNullable(updatedCustomerDTO.getName()).orElse(customer.getName()));
        customer.setPhone(Optional.ofNullable(updatedCustomerDTO.getPhone()).orElse(customer.getPhone()));
        customer.setEmail(Optional.ofNullable(updatedCustomerDTO.getEmail()).orElse(customer.getEmail()));
        customer.setBalance(Optional.ofNullable(updatedCustomerDTO.getBalance()).orElse(customer.getBalance()));
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void delete(long id) {
        customerRepository.findById(id).orElseThrow(() -> new NoSuchEntityExistsException("customer with id=" + id + " does not exist."));
        customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> fetchCustomerList(String name, String phone, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return customerRepository.findByNameAndPhone(name, phone, pageable);
    }
}
