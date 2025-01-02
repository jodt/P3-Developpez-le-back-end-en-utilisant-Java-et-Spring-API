package com.openclassrooms.chatop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;

    private String email;

    private String name;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
