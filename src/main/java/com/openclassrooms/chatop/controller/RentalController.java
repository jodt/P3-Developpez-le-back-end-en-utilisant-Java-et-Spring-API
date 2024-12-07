package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.RentalsDto;
import com.openclassrooms.chatop.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
