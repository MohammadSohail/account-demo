package com.example.accountdemo.service.Impl;

import com.example.accountdemo.dto.ExecutiveDto;
import com.example.accountdemo.model.Executive;
import com.example.accountdemo.model.User;
import com.example.accountdemo.repository.CustomerRepository;
import com.example.accountdemo.repository.ExecutiveRepository;
import com.example.accountdemo.repository.UserRepository;
import com.example.accountdemo.service.ExecutiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Slf4j
@Service
public class ExecutiveServiceImpl implements ExecutiveService {
    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public ExecutiveServiceImpl(CustomerRepository customerRepository, ExecutiveRepository executiveRepository, UserRepository userRepository, UserServiceImpl userService) {
        this.customerRepository = customerRepository;
        this.executiveRepository = executiveRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public String getExecutive(Model model) {
        User user = userService.getAuthUser();
        if (user ==null) {
            return "error";
        }
        model.addAttribute("appName","Exabyting Executive Web portal");
        Optional<Executive> executive = executiveRepository.findByUserId(user.getId());
        model.addAttribute("executive",executive.get());

        log.info("home view for executive: " + executive.get().toString());
        return "executive/home";
    }

    @Override
    public String addExecutive(ExecutiveDto executiveDto, Model model) {
        String status = "Success!";

        if (checkPassword(executiveDto))
        {
            log.info("password matched!");

            try {
                User user = userRepository.save(executiveDto.getUser());
                executiveDto.setUserId(user.getId());
                executiveRepository.save(executiveDto.getExecutive());

            } catch (DataIntegrityViolationException e)
            {
                status = "Failed!"+"\n"+e.getRootCause().getMessage();
            }

        } else {
            status="password didn't matched!";
        }

        log.error(status);
        model.addAttribute("status",status);
        return "executive/registration";
    }

    private boolean checkPassword(ExecutiveDto executiveDto) {
        return executiveDto.getPassword().equals(executiveDto.getRetypePassword()) && !executiveDto.getPassword().equals("");
    }
}
