package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.shorted.UserShortDto;
import com.karadyauran.conferenc.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserShortMapper
{
    UserShortDto toDto(User user);

    List<UserShortDto> toDtoList(List<User> users);

    User toEntity(UserShortDto dto);

    List<User> toEntityList(List<UserShortDto> dtos);
}
