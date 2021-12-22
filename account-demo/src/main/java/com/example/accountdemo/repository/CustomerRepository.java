package com.example.accountdemo.repository;

import com.example.accountdemo.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByUserId(long userId);
}
