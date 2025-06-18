package com.example.TechConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import com.example.TechConnect.model.EmailDto;
import com.example.TechConnect.service.EmailService;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String showForm(WebRequest request, Model model) {
        EmailDto email = new EmailDto();
        model.addAttribute("send", email);
        return "email/send";
    }

    @PostMapping("/send")
    public String sendEmail(@ModelAttribute("send") EmailDto emailDto, Model model) {
        try {
            emailService.sendSimpleEmail(
                    emailDto.getTo(),
                    emailDto.getSubject(),
                    emailDto.getBody());
            model.addAttribute("success", "Gửi email thành công tới " + emailDto.getTo());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi gửi email: " + e.getMessage());
        }

        return "email/send";
    }
}
