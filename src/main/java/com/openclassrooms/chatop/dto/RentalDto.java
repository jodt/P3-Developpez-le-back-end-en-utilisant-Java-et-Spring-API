package com.openclassrooms.chatop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDto {

    @NotBlank
    private String name;

    @NotNull
    private double surface;

    @NotNull
    private double price;

    @NotBlank
    private String description;

}
