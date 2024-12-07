package com.openclassrooms.chatop.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RentalsDto {

    private List<RentalDto> rentals;

}
