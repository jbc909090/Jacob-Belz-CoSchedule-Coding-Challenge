package com.example.coding.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.coding.challenge.models.User;
import com.example.coding.challenge.models.DTOs.LoginDTO;
import com.example.coding.challenge.models.DTOs.OutgoingUserDTO;
import com.example.coding.challenge.repository.UserRepository;

public class AuthService {
    UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public OutgoingUserDTO login(LoginDTO user) {
        Optional<User> check = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (check.isPresent()) {
            return new OutgoingUserDTO(check.get().getUsername(), check.get().getRole(), check.get().getUserId());
        }
        return null;
    }
    
    public OutgoingUserDTO register(LoginDTO user) {
        Optional<User> check = userRepository.findByUsername(user.getUsername());
        if (check.isEmpty()) {
            User registered = new User(user.getUsername(), user.getPassword());
            registered = userRepository.save(registered);
            return new OutgoingUserDTO(registered.getUsername(), registered.getRole(), registered.getUserId());
        }
        return null;
    }
}
