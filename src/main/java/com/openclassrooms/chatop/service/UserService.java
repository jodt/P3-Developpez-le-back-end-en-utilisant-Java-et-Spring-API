package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.RegisterRequestDto;
import com.openclassrooms.chatop.exception.UserAlreadyRegisteredException;
import com.openclassrooms.chatop.model.User;
import org.springframework.security.core.Authentication;

public interface UserService {

    User addUser(RegisterRequestDto registerRequest) throws UserAlreadyRegisteredException;
}
