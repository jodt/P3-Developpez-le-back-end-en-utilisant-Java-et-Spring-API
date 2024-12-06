package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.AuthSuccessDto;
import com.openclassrooms.chatop.dto.LoginRequestDto;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    public Authentication authenticate(String email, String password);

}
