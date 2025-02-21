package com.birimarung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("https://drink-pop.vercel.app", "http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        config.setAllowCredentials(true); // Allow credentials (cookies, etc.)
        source.registerCorsConfiguration("/**", config);  // Apply the config globally
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF for specific endpoints
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/waitlist/**"));
        http.authorizeRequests()
                .requestMatchers("/products/**", "/waitlist/**", "/**").permitAll()  // Allow all for CORS preflight
                .requestMatchers("/stores/**").authenticated()
                .anyRequest().authenticated();
        http.addFilterBefore(new CorsFilter(corsConfigurationSource()), SecurityContextHolderAwareRequestFilter.class);

        return http.build();
    }
}