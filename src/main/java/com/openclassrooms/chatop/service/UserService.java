package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.RegisterRequestDto;
import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.exception.UserAlreadyRegisteredException;
import com.openclassrooms.chatop.model.User;

public interface UserService {

    User addUser(RegisterRequestDto registerRequest) throws UserAlreadyRegisteredException;

    User getUserByMail(String email) throws ResourceNotFoundException;

    UserDto getUserDtoByMail(String email) throws ResourceNotFoundException;

    UserDto getUserDtoById(int id) throws ResourceNotFoundException;

    User getUserById(int id) throws ResourceNotFoundException;

}
