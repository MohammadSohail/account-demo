package com.example.accountdemo.dto;


import com.example.accountdemo.model.Customer;
import com.example.accountdemo.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    private long id;
    private long userId;
    private String address;
    private int balance;
    private String name;
    private String email;
    private String password;
    private String retypePassword;
    private int typeId;

    public Customer getCustomer() {
        return new Customer(this.getUserId(),
                this.getAddress(),
                this.balance,
                this.name,
                this.email);
    }

    public User getUser() {
        return new User(0,
                this.getEmail(),
                this.getPassword());
    }
}
