package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserCreateMapper
{
    UserCreateDto toDto(User user);

    List<UserCreateDto> toDtoList(List<User> users);

    User toEntity(UserCreateDto dto);

    List<User> toEntityList(List<UserCreateDto> dtos);
}
