package com.example.accountdemo.service;

import com.example.accountdemo.model.User;
import com.example.accountdemo.repository.UserRepository;
import com.example.accountdemo.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(s);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User Not Found!");
        return new UserDetailsServiceImpl(user.get());
    }
}
