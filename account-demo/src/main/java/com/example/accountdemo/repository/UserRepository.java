package com.example.accountdemo.repository;

import com.example.accountdemo.model.Customer;
import com.example.accountdemo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
