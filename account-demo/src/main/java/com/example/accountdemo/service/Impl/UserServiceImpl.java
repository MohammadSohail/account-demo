package com.example.accountdemo.service.Impl;

import com.example.accountdemo.dto.ExecutiveDto;
import com.example.accountdemo.model.User;
import com.example.accountdemo.repository.CustomerRepository;
import com.example.accountdemo.repository.ExecutiveRepository;
import com.example.accountdemo.repository.UserRepository;
import com.example.accountdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(CustomerRepository customerRepository, ExecutiveRepository executiveRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.executiveRepository = executiveRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String validateLogIn(ExecutiveDto logInData, Model model) {

        Optional<User> user = userRepository.findByEmail(logInData.getEmail());

        if (user.isPresent() && isPasswordMatch(logInData, user)) {

            log.info("logIn successful");
            return getHomePage(user.get());
        }
        model.addAttribute("status", "Failed!");
        log.error("login failed");
        return "home";
    }

    @Override
    public String returnHome(Model model) {

        User user = getAuthUser();
        if (user != null)
        {
            return getHomePage(user);
        }
        return "home";
    }

    private boolean isPasswordMatch(ExecutiveDto logInData, Optional<User> user) {
        return user.get().getPassword().equals(logInData.getPassword());
    }

    private String getHomePage(User user){
        if (user.getTypeId() == 0) {
            log.info("redirect to customer home page");
            return "redirect:/customer";
        } else {
            log.info("redirect to executive home page");
            return  "redirect:/executive";
        }
    }
    public User getAuthUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        log.info(String.valueOf(auth));
        log.info(auth.getName());
        return user.orElse(null);
    }
}
