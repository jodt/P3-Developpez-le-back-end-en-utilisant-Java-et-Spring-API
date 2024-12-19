package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.RentalCreateDto;
import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.dto.RentalResponseDto;
import com.openclassrooms.chatop.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalMapper {


    RentalDto asRentalDto(Rental rental);

    @Mapping(target = "ownerId", source = "user.id")
    RentalResponseDto asRentalResponseDto(Rental rental);


    @Mapping(target = "picture", ignore = true)
    Rental asRental(RentalCreateDto rentalCreateDto);

}
