package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.AuthSuccessDto;
import com.openclassrooms.chatop.dto.LoginRequestDto;
import com.openclassrooms.chatop.dto.RegisterRequestDto;
import com.openclassrooms.chatop.exception.UserAlreadyRegisteredException;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.AuthenticationService;
import com.openclassrooms.chatop.service.JwtService;
import com.openclassrooms.chatop.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService, JwtService jwtService) {

        this.authenticationService = authenticationService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthSuccessDto> login(@RequestBody LoginRequestDto loginRequest) {

        log.info("POST /login called -> start the process to log in the user");

        Authentication authentication =  this.authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        AuthSuccessDto token = AuthSuccessDto.builder()
                .token(this.jwtService.generateJwtToken(authentication.getName()))
                .build();

        log.info("User login successfully");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthSuccessDto> register(@Valid @RequestBody RegisterRequestDto registerRequest) throws UserAlreadyRegisteredException {

        log.info("POST /register called -> start the process register a new user with mail {}", registerRequest.getEmail());

        this.userService.addUser(registerRequest);

        AuthSuccessDto token = AuthSuccessDto.builder()
                .token(this.jwtService.generateJwtToken(registerRequest.getEmail()))
                .build();

        log.info("User registered successfully");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}