package com.openclassrooms.chatop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalDto {

    private int id;

    private String name;

    private double surface;

    private double price;

    private String picture;

    private String description;

    private int ownerId;

    private LocalDate createdAt;

    private LocalDate updatedAt;


}
