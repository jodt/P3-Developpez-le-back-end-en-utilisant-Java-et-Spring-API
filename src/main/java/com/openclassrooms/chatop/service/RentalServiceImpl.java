package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.RentalCreateDto;
import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.dto.RentalResponseDto;
import com.openclassrooms.chatop.dto.RentalsDto;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.exception.UnauthorizedActionException;
import com.openclassrooms.chatop.mapper.RentalMapper;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    private final UserService userService;

    private final FileStorageService fileStorageService;

    private final RentalMapper rentalMapper;

    @Value("${image.default}")
    private String DEFAUT_IMAGE;

    public RentalServiceImpl(RentalRepository rentalRepository, UserService userService, FileStorageService fileStorageService, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.userService = userService;
        this.fileStorageService = fileStorageService;
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
    public RentalResponseDto getRentalDtoById(int id) throws ResourceNotFoundException {
        log.info("Try to retrieve rental with id {}", id);
        return this.rentalRepository.findById(id).map(this.rentalMapper::asRentalResponseDto).orElseThrow(() -> {
            log.error("Rental with id {} not found", id);
            return new ResourceNotFoundException();
        });
    }

    @Override
    public Rental getRentalById(int id) throws ResourceNotFoundException {
        log.info("Try to retrieve rental with id {}", id);
        return this.rentalRepository.findById(id).orElseThrow(() -> {
            log.error("Rental with id {} not found", id);
            return new ResourceNotFoundException();
        });
    }

    @Override
    public Rental createRental(RentalCreateDto rentalToCreate, String userMail) throws ResourceNotFoundException {
        log.info("Try to create new rental for the user {}", userMail);
        Rental newRental = this.rentalMapper.asRental(rentalToCreate);
        User user = this.userService.getUserByMail(userMail);
        LocalDate now = LocalDate.now();
        newRental.setCreatedAt(now);
        newRental.setUpdatedAt(now);
        newRental.setUser(user);

        Rental rentalSaved = this.rentalRepository.save(newRental);

        String filePath;
        try {
            filePath = this.fileStorageService.saveFile(rentalToCreate.getPicture(), user.getId(), rentalSaved.getId());
        } catch (Exception ex) {
            filePath = DEFAUT_IMAGE;
        }

        rentalSaved.setPicture(filePath);
        return this.rentalRepository.save(rentalSaved);
    }

    @Override
    public void updateRental(int id, RentalDto rentalUpdated) throws ResourceNotFoundException, UnauthorizedActionException {
        log.info("Try to update rental with id {}", id);
        Optional<Rental> rental = this.rentalRepository.findById(id);

        if (rental.isPresent()) {
            Rental rentalToUpdate = rental.get();
            this.isUserAllowedToUpdateRental(rentalToUpdate);
            this.updateRentalWithNewData(rentalToUpdate, rentalUpdated);
            this.rentalRepository.save(rentalToUpdate);
        } else {
            log.error("Rental with id {} not found", id);
            throw new ResourceNotFoundException();
        }
    }

    private void updateRentalWithNewData(Rental rentalToUpdate, RentalDto rentalUpdated) {
        log.info("Update rental with id {} with new data", rentalToUpdate.getId());
        rentalToUpdate.setName(rentalUpdated.getName());
        rentalToUpdate.setSurface(rentalUpdated.getSurface());
        rentalToUpdate.setPrice(rentalUpdated.getPrice());
        rentalToUpdate.setDescription(rentalUpdated.getDescription());
        rentalToUpdate.setUpdatedAt(LocalDate.now());
    }

    private void isUserAllowedToUpdateRental(Rental rental) throws UnauthorizedActionException {
        log.info("Check if user is allowed to update the rental");
        String loggedUserMail = SecurityContextHolder.getContext().getAuthentication().getName();
        String rentalOwnerMail = rental.getUser().getEmail();
        if (!loggedUserMail.equals(rentalOwnerMail)) {
            log.error("The user {} is not allowed to update the rental owned by {}", loggedUserMail, rentalOwnerMail);
            throw new UnauthorizedActionException();
        }
    }
}
