package com.example.accountdemo.controller;

import com.example.accountdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LoginController {

    private final UserService userService;
    @Value("${spring.application.name}")
    String appName;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return userService.returnHome(model);
    }

    @GetMapping("/signin")
    public String signInPage(Model model) {
        return "home";
    }

}
