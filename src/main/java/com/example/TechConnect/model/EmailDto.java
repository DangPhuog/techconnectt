package com.example.TechConnect.model;

import jakarta.validation.constraints.NotBlank;

public class EmailDto {
    @NotBlank(message = "khong duoc trong")
    private String to;

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    @NotBlank(message = "khong duoc trong")
    private String subject;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @NotBlank(message = "khong duoc trong")
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
