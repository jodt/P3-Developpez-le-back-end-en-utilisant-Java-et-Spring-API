package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user information", description = "Return user information by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())})
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserDto> getUser(@Parameter(description = "User ID", example = "1") @PathVariable int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(this.userService.getUserDtoById(id), HttpStatus.OK);
    }

}
