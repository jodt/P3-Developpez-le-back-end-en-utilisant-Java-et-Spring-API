package com.openclassrooms.chatop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@Data
@EqualsAndHashCode(callSuper = true)
public class RentalCreateDto extends RentalDto {
    private MultipartFile picture;
}
