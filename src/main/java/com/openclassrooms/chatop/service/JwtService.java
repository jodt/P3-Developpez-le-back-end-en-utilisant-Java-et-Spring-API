package com.openclassrooms.chatop.service;

import org.springframework.security.core.Authentication;

public interface JwtService {

    public String generateJwtToken(String email);

}
