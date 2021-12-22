package com.example.accountdemo.service.Impl;

import com.example.accountdemo.dto.CustomerDto;
import com.example.accountdemo.dto.TransferFormDto;
import com.example.accountdemo.model.Customer;
import com.example.accountdemo.model.User;
import com.example.accountdemo.repository.CustomerRepository;
import com.example.accountdemo.repository.ExecutiveRepository;
import com.example.accountdemo.repository.UserRepository;
import com.example.accountdemo.service.CustomerService;
import com.example.accountdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;

    private final UserServiceImpl userService;

    public CustomerServiceImpl(CustomerRepository customerRepository, ExecutiveRepository executiveRepository, UserRepository userRepository, UserServiceImpl userService) {
        this.customerRepository = customerRepository;
        this.executiveRepository = executiveRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public String getCustomer(Model model) {

        User user = userService.getAuthUser();
        if (user ==null) return "error";

        model.addAttribute("appName", "Exabyting Customer Web portal");
        Optional<Customer> customer = customerRepository.findByUserId(user.getId());
        model.addAttribute("customer", customer.get());

        log.info("customers home view return for: " + customer.get().toString());
        return "customer/home";
    }

    @Override
    public String getCustomersList(Model model) {

        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        model.addAttribute("customers", customers);
        log.info("customers list view return");
        return "customer/view";
    }

    @Override
    public String addCustomer(CustomerDto customer, Model model) {

        String status = "Success!";
        if (checkPassword(customer)) {
            log.info("password matched!");

            try {
                User user = userRepository.save(customer.getUser());
                customer.setUserId(user.getId());
                customerRepository.save(customer.getCustomer());

            } catch (DataIntegrityViolationException e) {
                status = "Failed!" + "\n" + e.getRootCause().getMessage();
            }

        } else {
            status = "password didn't matched!";
        }

        log.error(status);
        model.addAttribute("status", status);
        return "customer/registration";
    }

    @Override
    public String transferView(Model model) {
        User user = userService.getAuthUser();
        if (user ==null) {
            return "error";
        }
        model.addAttribute("appName", "Exabyting Customer Web portal");
        Optional<Customer> customer = customerRepository.findByUserId(user.getId());
        model.addAttribute("customer", customer.get());

        log.info("goto transfer view");
        return "customer/transfer";
    }

    @Override
    public String transferMoney(TransferFormDto transferFormDto, Model model) {
        model.addAttribute("appName", "Exabyting Customer Web portal");
        String status = "Failed!";

        Optional<Customer> customer = customerRepository.findByUserId(transferFormDto.getUserId());
        Optional<User> user = userRepository.findById(transferFormDto.getUserId());
        Optional<User> desUser = userRepository.findByEmail(transferFormDto.getDestinationEmail());
        Optional<Customer> desCustomer = customerRepository.findByUserId(desUser.get().getId());


        if (checkCustomer(customer, user, desCustomer)) {
            Customer customer1 = customer.get();
            Customer customerDes = desCustomer.get();
            User user1 = user.get();

            if (checkTransactionCondition(transferFormDto, customer1, user1)) {
                status = "Success!";
                transferBalance(transferFormDto, customer1, customerDes);
            }
        }

        model.addAttribute("customer", customer.get());
        model.addAttribute("status", status);
        return "customer/transfer";
    }

    private void transferBalance(TransferFormDto transferFormDto, Customer customer1, Customer customerDes) {
        customer1.setBalance(customer1.getBalance() - transferFormDto.getTransferMoney());
        customerDes.setBalance(transferFormDto.getTransferMoney() + customerDes.getBalance());
        customerRepository.save(customer1);
        customerRepository.save(customerDes);
    }

    private boolean checkTransactionCondition(TransferFormDto transferFormDto, Customer customer1, User user1) {
        return customer1.getBalance() >= transferFormDto.getTransferMoney() && user1.getPassword().equals(transferFormDto.getPassword());
    }

    private boolean checkCustomer(Optional<Customer> customer, Optional<User> user, Optional<Customer> desCustomer) {
        return customer.isPresent() && user.isPresent() && desCustomer.isPresent();
    }

    private boolean checkPassword(CustomerDto customer) {
        return customer.getPassword().equals(customer.getRetypePassword()) && !customer.getPassword().equals("");
    }
}
