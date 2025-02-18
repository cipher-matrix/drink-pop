package com.birimarung.services;


import com.birimarung.dto.AuthenticationDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityLogic {
    @Value("${SECURITY_USER}")
    private String username;
    @Value("${SECURITY_PASSWORD}")
    private String password;

    public AuthenticationDTO getAuthentication() {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setUsername(username);
        authenticationDTO.setPassword(password);
        return authenticationDTO;

    }

}
