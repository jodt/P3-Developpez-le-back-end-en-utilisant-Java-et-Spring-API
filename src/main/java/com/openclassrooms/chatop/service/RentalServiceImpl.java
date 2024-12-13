package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.dto.RentalResponseDto;
import com.openclassrooms.chatop.dto.RentalsDto;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.mapper.RentalMapper;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Slf4j
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final UserService userService;
    private final RentalMapper rentalMapper;

    public RentalServiceImpl(RentalRepository rentalRepository, UserService userService, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.userService = userService;
        this.rentalMapper = rentalMapper;
    }

    @Override
    public RentalsDto getAllRentals() {
        log.info("Try to retrieve all rentals");
        List<Rental> rentals = this.rentalRepository.findAll();

        List<RentalResponseDto> allRentals = rentals.stream()
                    .map(rentalMapper::asRentalResponseDto)
                    .toList();

        return RentalsDto.builder().rentals(allRentals).build();
    }

    @Override
    public RentalResponseDto getRentalById(int id) throws ResourceNotFoundException {
        log.info("Try to retrieve rental with id {}", id);
        return this.rentalRepository.findById(id).map(this.rentalMapper::asRentalResponseDto).orElseThrow(() -> {
            log.error("Rental with id {} not found", id);
            return new ResourceNotFoundException();
        });
    }

    @Override
    public Rental createRental(RentalDto rentalDto, String userMail) throws ResourceNotFoundException {
        log.info("Try to create new rental for the user {}", userMail);
        Rental newRental = this.rentalMapper.asRental(rentalDto);
        User user = this.userService.getUserByMail(userMail);
        LocalDate now = LocalDate.now();
        newRental.setCreatedAt(now);
        newRental.setUpdatedAt(now);
        newRental.setUser(user);
        return this.rentalRepository.save(newRental);
    }
}
