package com.example.accountdemo.controller;

import com.example.accountdemo.dto.CustomerDto;
import com.example.accountdemo.service.CustomerService;
import com.example.accountdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("")
    public String home(Model model){

        return customerService.getCustomer(model);
    }


    @GetMapping("/registration")
    public String registration(Model model){
        return "customer/registration";
    }

    @PostMapping("/add")
    public String addCustomer(CustomerDto customer, Model model){

        return customerService.addCustomer(customer, model);
    }

    @GetMapping("/view")
    public String view(Model model){

        return customerService.getCustomersList( model);
    }
}
