package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.RegisterRequestDto;
import com.openclassrooms.chatop.exception.UserAlreadyRegisteredException;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;
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

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    private void isUserAlreadyRegister(String email) throws UserAlreadyRegisteredException {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.error("User is already registered");
            throw new UserAlreadyRegisteredException();
        }
    }
}


