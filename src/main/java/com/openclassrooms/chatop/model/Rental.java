package com.openclassrooms.chatop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
