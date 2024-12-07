package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.model.Rental;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    RentalDto asRentalDto(Rental rental);

}
