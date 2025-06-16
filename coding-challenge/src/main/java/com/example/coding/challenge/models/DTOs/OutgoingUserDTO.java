package com.example.coding.challenge.models.DTOs;

public class OutgoingUserDTO {
    //do not send the password back for security reasons
    private int userId;
    private String username;
    private String role;
    //----------------- all boilerplate below this line -----------
    public OutgoingUserDTO(String username, String role, int userId) {
        this.username = username;
        this.role = role;
        this.userId = userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "OutgoingUserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
