package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.*;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.service.RentalService;
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
    public ResponseEntity<RentalsDto> getAllRentals() {
        log.info("GET /rentals called -> start the process to retrieve all rentals");
        RentalsDto allRentals = this.rentalService.getAllRentals();
        log.info("All rentals retrieved successfully");
        return new ResponseEntity<>(allRentals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDto> getRentalById(@PathVariable int id) throws ResourceNotFoundException {
        log.info("GET /rental/{} called -> start the process to retrieve rental with id {}", id, id);
        RentalResponseDto rental = this.rentalService.getRentalDtoById(id);
        log.info("Rental with id {} retrieved successfully", id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createRental(@ModelAttribute RentalCreateDto rentalCreateDto, Authentication authentication) throws ResourceNotFoundException {
        log.info("POST /rentals called -> start the process to add a new rental");
        this.rentalService.createRental(rentalCreateDto, authentication.getName());
        log.info("Rental created successfully");
        ResponseDto response = ResponseDto.builder().message("Rental created !").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updateRental(@PathVariable int id, @ModelAttribute RentalDto rentalUpdated) throws ResourceNotFoundException {
        log.info("PUT /rental/{} called -> start the process to update rental with id {}", id, id);
        this.rentalService.updateRental(id, rentalUpdated);
        log.info("Rental updated successfully");
        ResponseDto response = ResponseDto.builder().message("Rental updated !").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
