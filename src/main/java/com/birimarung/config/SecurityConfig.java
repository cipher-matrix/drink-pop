package com.birimarung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/products/**").permitAll() // Allow access to /public/** endpoints without authentication
                .requestMatchers("/admin/**").authenticated() // Require authentication for /admin/** endpoints
                .anyRequest().authenticated();

        return http.build();
    }
}