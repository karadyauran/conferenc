package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.UserDto;
import com.karadyauran.conferenc.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "events", source = "events"),
            @Mapping(target = "bookings", source = "bookings")
    })
    UserDto toDto(User user);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "events", source = "events"),
            @Mapping(target = "bookings", source = "bookings")
    })
    List<UserDto> toDtoList(List<User> users);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "events", source = "events"),
            @Mapping(target = "bookings", source = "bookings")
    })
    User toEntity(UserDto dto);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "events", source = "events"),
            @Mapping(target = "bookings", source = "bookings")
    })
    List<User> toEntityList(List<UserDto> dtos);
}
