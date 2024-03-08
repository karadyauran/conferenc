package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.UserDto;
import com.karadyauran.conferenc.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);

    User toEntity(UserDto dto);

    List<User> toEntityList(List<UserDto> dtos);
}
