package com.example.coding.challenge.controller;

import com.example.coding.challenge.models.DTOs.LoginDTO;
import com.example.coding.challenge.models.DTOs.OutgoingUserDTO;
import com.example.coding.challenge.models.User;
import com.example.coding.challenge.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") //Requests ending in /auth will go to this Controller
public class AuthController {
    private final AuthService authService;

    //Set up the beans for the service import
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //register a new user
    @PostMapping("/register")
    public ResponseEntity<OutgoingUserDTO> register(@RequestBody LoginDTO user) {
        OutgoingUserDTO returnedUser = authService.register(user);
        return ResponseEntity.ok(returnedUser);
    }
    //Login to an existing user
    @PostMapping("/login")
    public ResponseEntity<OutgoingUserDTO> login(@RequestBody LoginDTO user, HttpSession session){
        OutgoingUserDTO loggedInUser = authService.login(user);
        session.setAttribute("userId", loggedInUser.getUserId());
        session.setAttribute("username", loggedInUser.getUsername());
        session.setAttribute("role", loggedInUser.getRole());
        return ResponseEntity.ok(loggedInUser);
    }
}
