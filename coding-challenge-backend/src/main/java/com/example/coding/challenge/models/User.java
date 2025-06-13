package com.example.coding.challenge.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Users")
public class User {
    //the private key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    //Usernames must exist and must be unique
    @Column(unique = true, nullable = false)
    private String username;

    //passwords must exist but can be the same as other passwords
    @Column(nullable = false)
    private String password;

    //to determine access level
    private String role = "user";

    //----------------- all boilerplate below this line -----------
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
