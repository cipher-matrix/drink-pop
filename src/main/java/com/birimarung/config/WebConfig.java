package com.birimarung.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all endpoints
                .allowedOrigins("http://localhost:4200")  // Allow requests from localhost:4200
                .allowedMethods("GET", "POST", "OPTIONS")  // Allow GET, POST, and OPTIONS methods
                .allowedHeaders("Content-Type", "X-CSRF-TOKEN")  // Allow specific headers
                .allowCredentials(true);  // Allow credentials (if needed)
    }
}