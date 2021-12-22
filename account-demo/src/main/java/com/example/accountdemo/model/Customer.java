package com.example.accountdemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int balance;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;


    public Customer( long userId, String address, int
            balance, String name, String email) {
        this.userId = userId;
        this.address = address;
        this.balance = balance;
        this.name = name;
        this.email = email;
    }
}
