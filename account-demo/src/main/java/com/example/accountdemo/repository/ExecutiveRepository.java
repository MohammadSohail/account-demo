package com.example.accountdemo.repository;

import com.example.accountdemo.model.Customer;
import com.example.accountdemo.model.Executive;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExecutiveRepository extends CrudRepository<Executive, Long> {
    Optional<Executive> findByUserId(long userId);
}
