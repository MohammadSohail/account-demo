package com.example.accountdemo.controller;

import com.example.accountdemo.dto.ExecutiveDto;
import com.example.accountdemo.service.ExecutiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
public class RegistrationController {

    private final ExecutiveService executiveService;

    public RegistrationController(ExecutiveService executiveService) {
        this.executiveService = executiveService;
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        log.info("goto executive registration view");
        return "executive/registration";
    }

    @PostMapping("/add")
    public String addExecutive(ExecutiveDto executiveDto, Model model) {

        return executiveService.addExecutive(executiveDto, model);
    }

    @GetMapping("/executive")
    public String home(Model model) {

        return executiveService.getExecutive(model);
    }
}
