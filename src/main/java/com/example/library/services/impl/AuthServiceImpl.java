package com.example.library.services.impl;

import com.example.library.models.User;
import com.example.library.models.exceptions.InvalidArgumentsException;
import com.example.library.models.exceptions.InvalidUserCredentialsException;
import com.example.library.repositories.UserRepository;
import com.example.library.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}
