package com.example.customer_account_management.repository;

import com.example.customer_account_management.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c where c.name like concat('%',?1,'%')and c.phone like concat('%',?2,'%' ) ")
    Page<Customer> findByNameAndPhone(String name, String phone, Pageable pageable);
}
