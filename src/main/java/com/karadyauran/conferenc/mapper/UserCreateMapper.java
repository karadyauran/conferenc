package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserCreateMapper
{
    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "role", source = "role"),
            @Mapping(target = "password", source = "password")
    })
    UserCreateDto toDto(User user);

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "role", source = "role"),
            @Mapping(target = "password", source = "password")
    })
    List<UserCreateDto> toDtoList(List<User> users);

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "role", source = "role"),
            @Mapping(target = "password", source = "password")
    })
    User toEntity(UserCreateDto dto);

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "role", source = "role"),
            @Mapping(target = "password", source = "password")
    })
    List<User> toEntityList(List<UserCreateDto> dtos);
}
