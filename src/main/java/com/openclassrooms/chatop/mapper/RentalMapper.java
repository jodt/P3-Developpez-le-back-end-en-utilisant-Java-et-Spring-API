package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    @Mapping(target = "ownerId", source = "user.id")
    RentalDto asRentalDto(Rental rental);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    Rental asRental(RentalDto rentalDto);

}
