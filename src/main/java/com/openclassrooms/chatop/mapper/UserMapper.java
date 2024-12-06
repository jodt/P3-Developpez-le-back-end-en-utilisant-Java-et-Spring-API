package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto asUserDto(User user);
}
