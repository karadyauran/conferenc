package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.SessionCreateDto;
import com.karadyauran.conferenc.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionCreateMapper
{
    @Mappings({
            @Mapping(target = "eventId", source = "eventId"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "speaker", source = "speaker"),
            @Mapping(target = "location", source = "location")
    })
    SessionCreateDto toDto(Session session);

    @Mappings({
            @Mapping(target = "eventId", source = "eventId"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "speaker", source = "speaker"),
            @Mapping(target = "location", source = "location")
    })
    List<SessionCreateDto> toDtoList(List<Session> sessions);

    @Mappings({
            @Mapping(target = "eventId", source = "eventId"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "speaker", source = "speaker"),
            @Mapping(target = "location", source = "location")
    })
    Session toEntity(SessionCreateDto dto);

    @Mappings({
            @Mapping(target = "eventId", source = "eventId"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "speaker", source = "speaker"),
            @Mapping(target = "location", source = "location")
    })
    List<Session> toEntityList(List<SessionCreateDto> dtos);
}
