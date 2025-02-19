package com.birimarung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                        .ignoringRequestMatchers("/waitlist/**") // Disable CSRF for this path
                )
                .authorizeRequests()
                .requestMatchers("/products/**", "/waitlist/**").permitAll()
                .requestMatchers("/stores/**").authenticated()
                .anyRequest().authenticated().and().httpBasic(withDefaults());
        return http.build();
    }
}