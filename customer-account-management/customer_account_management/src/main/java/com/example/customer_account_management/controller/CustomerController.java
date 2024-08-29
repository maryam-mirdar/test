package com.example.customer_account_management.controller;

import com.example.customer_account_management.model.dto.CustomerDTO;
import com.example.customer_account_management.model.entity.Customer;
import com.example.customer_account_management.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("/customer")
public class CustomerController {

    final CustomerService customerService;

    @PostMapping("/add")
    public Customer add(@RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }

    @PutMapping("/update/{id}")
    public Customer update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.update(id, customerDTO);
    }

    @DeleteMapping("/delete/{customerId}")
    public String delete(@PathVariable("customerId") long id) {
        customerService.delete(id);
        return "Deleted Successfully";
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> filter(@RequestParam(defaultValue = "") String name,
                                                 @RequestParam(defaultValue = "") String phone,
                                                 @RequestParam(defaultValue = "0") int pageNo,
                                                 @RequestParam(defaultValue = "5") int pageSize) {
        Page<Customer> customers = customerService.fetchCustomerList(name, phone, pageNo, pageSize);
        return ResponseEntity.ok(customers);
    }
}
