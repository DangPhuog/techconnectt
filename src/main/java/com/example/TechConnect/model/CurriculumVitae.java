package com.example.TechConnect.model;

import jakarta.validation.constraints.NotBlank;

public class CurriculumVitae {
    @NotBlank(message = "khong duoc trong")
    private String name;

    @NotBlank(message = "khong duoc trong")
    private String email;

    @NotBlank(message = "khong duoc trong")
    private String phone;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    // private boolean enabled = false; // chưa xác thực ban đầu
}
