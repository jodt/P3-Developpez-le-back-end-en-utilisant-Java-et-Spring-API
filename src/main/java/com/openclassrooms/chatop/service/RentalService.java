package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.dto.RentalsDto;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;

public interface RentalService {

    RentalsDto getAllRentals ();

    RentalDto getRentalById (int id) throws ResourceNotFoundException;

}
