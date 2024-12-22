package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.*;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.exception.UserAlreadyRegisteredException;
import com.openclassrooms.chatop.service.AuthenticationService;
import com.openclassrooms.chatop.service.JwtService;
import com.openclassrooms.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Generate a token", description = "Generate a token when user tries to login if authenticated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuthSuccessDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"message\": \"error\" }"),
                    schema = @Schema(implementation = ResponseDto.class)))})
    @PostMapping("/login")
    public ResponseEntity<AuthSuccessDto> login(@RequestBody LoginRequestDto loginRequest) {

        log.info("POST api/auth/login called -> start the process to log in the user");

        Authentication authentication = this.authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        AuthSuccessDto token = AuthSuccessDto.builder()
                .token(this.jwtService.generateJwtToken(authentication.getName()))
                .build();

        log.info("User login successfully");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Operation(summary = "Register a new user", description = "Register a new user in the database and generate a token for them")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuthSuccessDto.class))}),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json"))})
    @PostMapping("/register")
    public ResponseEntity<AuthSuccessDto> register(@Valid @RequestBody RegisterRequestDto registerRequest) throws UserAlreadyRegisteredException {

        log.info("POST api/auth/register called -> start the process register a new user with mail {}", registerRequest.getEmail());

        this.userService.addUser(registerRequest);

        AuthSuccessDto token = AuthSuccessDto.builder()
                .token(this.jwtService.generateJwtToken(registerRequest.getEmail()))
                .build();

        log.info("User registered successfully");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Operation(summary = "Get user information", description = "Return logged in user information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())})
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/me")
    public ResponseEntity<UserDto> userInfo(Authentication authentication) throws ResourceNotFoundException {
        log.info("GET api/auth/me called -> start the process to get user info for user with mail {}", authentication.getName());
        UserDto userInfo = this.userService.getUserDtoByMail(authentication.getName());
        log.info("user information retrieved successfully ");
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

}
