package com.example.TechConnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        private final CustomLoginSuccessHandler loginSuccessHandler;

        public WebSecurityConfig(CustomLoginSuccessHandler loginSuccessHandler) {
                this.loginSuccessHandler = loginSuccessHandler;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((requests) -> requests
                                                .requestMatchers("/", "/register", "/register/**")
                                                .permitAll()
                                                .requestMatchers("/admin/**", "/").hasRole("ADMIN")
                                                .requestMatchers("/candidate/**", "/").hasRole("CANDIDATE")
                                                .anyRequest().authenticated())
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .successHandler(loginSuccessHandler)
                                                .permitAll())
                                .logout((logout) -> logout.permitAll());

                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                UserDetails employer = User.withDefaultPasswordEncoder()
                                .username("employer")
                                .password("employer")
                                .roles("EMPLOYER")
                                .build();

                UserDetails admin = User.withDefaultPasswordEncoder()
                                .username("admin")
                                .password("592002")
                                .roles("ADMIN")
                                .build();

                UserDetails candidate = User.withDefaultPasswordEncoder()
                                .username("candidate")
                                .password("candidate")
                                .roles("CANDIDATE")
                                .build();

                return new InMemoryUserDetailsManager(employer, admin, candidate);
        }
}