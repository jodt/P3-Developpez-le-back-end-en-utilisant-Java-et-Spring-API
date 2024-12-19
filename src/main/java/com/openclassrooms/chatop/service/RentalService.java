package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.RentalCreateDto;
import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.dto.RentalResponseDto;
import com.openclassrooms.chatop.dto.RentalsDto;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.model.Rental;

public interface RentalService {

    RentalsDto getAllRentals ();

    RentalResponseDto getRentalById (int id) throws ResourceNotFoundException;

    Rental createRental (RentalCreateDto newRental, String userMail) throws ResourceNotFoundException;

    void updateRental (int id, RentalDto rental) throws ResourceNotFoundException;

}
