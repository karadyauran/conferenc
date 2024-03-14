package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.shorted.UserShortDto;
import com.karadyauran.conferenc.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserShortMapper
{
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username")
    })
    UserShortDto toDto(User user);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username")
    })
    List<UserShortDto> toDtoList(List<User> users);
}
