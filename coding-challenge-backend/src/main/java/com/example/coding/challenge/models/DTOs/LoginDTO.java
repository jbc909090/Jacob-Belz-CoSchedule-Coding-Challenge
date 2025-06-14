package com.example.coding.challenge.models.DTOs;

public class LoginDTO {
    private String username;
    private String password;

    //----------------- all boilerplate below this line -----------
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
