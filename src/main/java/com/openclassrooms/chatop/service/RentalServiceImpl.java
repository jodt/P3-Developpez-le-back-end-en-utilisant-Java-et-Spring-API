package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.dto.RentalsDto;
import com.openclassrooms.chatop.mapper.RentalMapper;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    @Override
    public RentalsDto getAllRentals() {
        log.info("Try to retrieve all rentals");
        List<Rental> rentals = this.rentalRepository.findAll();

        List<RentalDto> allRentals = rentals.stream()
                    .map(rentalMapper::asRentalDto)
                    .toList();

        return RentalsDto.builder().rentals(allRentals).build();
    }
}
