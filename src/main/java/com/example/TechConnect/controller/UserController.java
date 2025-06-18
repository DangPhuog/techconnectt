package com.example.TechConnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.Authentication;
import com.example.TechConnect.model.UserDto;
import org.springframework.beans.factory.annotation.Value;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Value("${google.recaptcha.site}")
    private String recaptchaSiteKey;

    @Value("${google.recaptcha.secret}")
    private String recaptchaSecretKey;

    public static boolean isLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated()
                && !(auth instanceof org.springframework.security.authentication.AnonymousAuthenticationToken);
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("recaptchaSiteKey", recaptchaSiteKey);
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(WebRequest request, Model model) {
        if (isLoggedIn()) {
            return "redirect:/";
        }
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "register"; // quay lại trang form với lỗi hiển thị
        }

        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            model.addAttribute("passwordError", "Mật khẩu và mật khẩu xác nhận không khớp!");
            return "register";
        } else {
            // Nếu dữ liệu hợp lệ, controller sẽ tiếp tục thực thi
            model.addAttribute("registerSuccess", "Tạo tài khoản thành công!");
            return "login";
        }
    }
}
