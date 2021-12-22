package com.example.accountdemo.service;

import com.example.accountdemo.dto.ExecutiveDto;
import org.springframework.ui.Model;

public interface UserService {
    String validateLogIn(ExecutiveDto logInData, Model model);

    String returnHome(Model model);
}
