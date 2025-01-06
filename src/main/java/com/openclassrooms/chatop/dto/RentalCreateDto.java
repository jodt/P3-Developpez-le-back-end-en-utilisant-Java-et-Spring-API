package com.openclassrooms.chatop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@Data
@EqualsAndHashCode(callSuper = true)
public class RentalCreateDto extends RentalDto {

    @NotNull
    private MultipartFile picture;
}
