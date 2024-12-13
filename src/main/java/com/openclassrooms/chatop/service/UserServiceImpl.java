package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.RegisterRequestDto;
import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.exception.UserAlreadyRegisteredException;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.mapper.UserMapper;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public User addUser(RegisterRequestDto registerRequest) throws UserAlreadyRegisteredException {
        log.info("Check if user is already registered");
        this.isUserAlreadyRegister(registerRequest.getEmail());

        User userToSave = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(this.passwordEncoder.encode(registerRequest.getPassword()))
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();

        log.info("Save user {} in database", userToSave.getEmail());
        return this.userRepository.save(userToSave);
    }

    @Override
    public UserDto getUserByMail(String email) throws ResourceNotFoundException {
        log.info("Try to get user with mail {}", email);
        return this.userRepository.findByEmail(email).map(userMapper::asUserDto).orElseThrow(() -> {
            log.error("USer with mail {} not found", email);
            return new ResourceNotFoundException();
        });
    }

    @Override
    public int getUserIdByEmail(String email) throws ResourceNotFoundException {
        log.info("Try to get user id with mail {}", email);
        return this.userRepository.findByEmail(email).map(User::getId).orElseThrow(()-> {
            log.error("User with mail {} not found", email);
            return new ResourceNotFoundException();
                }
        );
    }

    private void isUserAlreadyRegister(String email) throws UserAlreadyRegisteredException {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.error("User is already registered");
            throw new UserAlreadyRegisteredException();
        }
    }
}


