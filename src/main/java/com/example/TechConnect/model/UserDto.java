package com.example.TechConnect.model;

import jakarta.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank(message = "khong duoc trong")
    private String firstName;

    @NotBlank(message = "khong duoc trong")
    private String lastName;

    @NotBlank(message = "khong duoc trong")
    private String password;

    private String matchingPassword;

    @NotBlank(message = "khong duoc trong")
    private String email;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
