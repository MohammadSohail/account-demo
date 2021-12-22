package com.example.accountdemo.controller;

import com.example.accountdemo.dto.TransferFormDto;
import com.example.accountdemo.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer")
public class TransferMoneyController {

    private final CustomerService customerService;

    public TransferMoneyController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public String home(Model model) {

        return customerService.transferView(model);
    }

    @PostMapping("/process")
    public String process(TransferFormDto transferFormDto, Model model) {

        return customerService.transferMoney(transferFormDto, model);
    }
}
