package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.*;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@RestController
@Slf4j
@RequestMapping("api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    @Operation(summary = "Get rentals", description = "Return a list of all rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = RentalsDto.class))}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())})
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<RentalsDto> getAllRentals() {
        log.info("GET api/rentals called -> start the process to retrieve all rentals");
        RentalsDto allRentals = this.rentalService.getAllRentals();
        log.info("All rentals retrieved successfully");
        return new ResponseEntity<>(allRentals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a rental", description = "Return a rental by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = RentalResponseDto.class))}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())})
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<RentalResponseDto> getRentalById(@Parameter(description = "Rental ID", example = "1") @PathVariable int id) throws ResourceNotFoundException {
        log.info("GET api/rentals/{} called -> start the process to retrieve rental with id {}", id, id);
        RentalResponseDto rental = this.rentalService.getRentalDtoById(id);
        log.info("Rental with id {} retrieved successfully", id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "Create a rental", description = "Create a new rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"message\": \"Rental created !\" }"),
                    schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())})
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ResponseDto> createRental(@Valid @ModelAttribute RentalCreateDto rentalCreateDto, Authentication authentication) throws ResourceNotFoundException {
        log.info("POST api/rentals called -> start the process to add a new rental");
        this.rentalService.createRental(rentalCreateDto, authentication.getName());
        log.info("Rental created successfully");
        ResponseDto response = ResponseDto.builder().message("Rental created !").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "{id}", consumes = "multipart/form-data")
    @Operation(summary = "Update rental", description = "Update a rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"message\": \"Rental updated !\" }"),
                    schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())})
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ResponseDto> updateRental(@Parameter(description = "Rental ID", example = "1") @PathVariable int id, @Valid @ModelAttribute RentalDto rentalUpdated) throws ResourceNotFoundException {
        log.info("PUT api/rentals/{} called -> start the process to update rental with id {}", id, id);
        this.rentalService.updateRental(id, rentalUpdated);
        log.info("Rental updated successfully");
        ResponseDto response = ResponseDto.builder().message("Rental updated !").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
