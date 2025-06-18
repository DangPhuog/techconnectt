package com.example.TechConnect.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/default";

        for (GrantedAuthority authority : authorities) {
            switch (authority.getAuthority()) {
                case "ROLE_ADMIN":
                    redirectUrl = "/admin";
                    break;
                case "ROLE_EMPLOYER":
                    redirectUrl = "/";
                    break;
                case "ROLE_CANDIDATE":
                    redirectUrl = "/";
                    break;
            }
        }

        response.sendRedirect(redirectUrl);
    }
}
