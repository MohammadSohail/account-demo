package com.example.accountdemo.service;

import com.example.accountdemo.dto.CustomerDto;
import com.example.accountdemo.dto.TransferFormDto;
import org.springframework.ui.Model;

public interface CustomerService {
    String getCustomer(Model model);

    String getCustomersList(Model model);

    String addCustomer(CustomerDto customer, Model model);

    String transferView(Model model);

    String transferMoney(TransferFormDto transferFormDto, Model model);
}
