package com.example.accountdemo.dto;

import com.example.accountdemo.model.Executive;
import com.example.accountdemo.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExecutiveDto {

    private long id;
    private long userId;
    private String name;
    private String email;
    private String password;
    private String retypePassword;
    private int typeId;

    public Executive getExecutive() {
        return Executive.builder().userId(this.getUserId()).name(this.getName()).email(this.getEmail()).build();
    }

    public User getUser() {
        return new User(1,
                this.getEmail(),
                this.getPassword());
    }
}
