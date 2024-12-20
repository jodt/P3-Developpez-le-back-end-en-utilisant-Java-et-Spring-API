package com.openclassrooms.chatop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessageDto {

    private int rentalId;

    private int userId;

    @NotBlank
    private String message;

}
