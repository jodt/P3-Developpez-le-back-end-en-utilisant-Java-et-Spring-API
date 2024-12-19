package com.openclassrooms.chatop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    int id;

    String email;

    String name;

    LocalDate createdAt;

    LocalDate updatedAt;
}
