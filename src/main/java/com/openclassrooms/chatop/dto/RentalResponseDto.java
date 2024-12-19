package com.openclassrooms.chatop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class RentalResponseDto extends RentalDto {

    private int id;

    private int ownerId;

    private String picture;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
