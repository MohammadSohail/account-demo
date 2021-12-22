package com.example.accountdemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransferFormDto {

    private long userId;
    private String password;
    private String destinationEmail;
    private int transferMoney;

}
