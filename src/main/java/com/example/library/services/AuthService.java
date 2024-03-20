package com.example.library.services;

import com.example.library.models.User;

public interface AuthService {
    User login(String username, String password);
}
