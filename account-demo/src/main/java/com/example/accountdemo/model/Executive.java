package com.example.accountdemo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Builder
public class Executive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

}
