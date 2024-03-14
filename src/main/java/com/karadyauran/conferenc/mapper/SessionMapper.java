package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.SessionDto;
import com.karadyauran.conferenc.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper
{
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "event", source = "event"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "speaker", source = "speaker"),
            @Mapping(target = "location", source = "location")
    })
    SessionDto toDto(Session session);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "event", source = "event"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "speaker", source = "speaker"),
            @Mapping(target = "location", source = "location")
    })
    List<SessionDto> toDtoList(List<Session> sessions);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "event", source = "event"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "speaker", source = "speaker"),
            @Mapping(target = "location", source = "location")
    })
    Session toEntity(SessionDto dto);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "event", source = "event"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "speaker", source = "speaker"),
            @Mapping(target = "location", source = "location")
    })
    List<Session> toEntityList(List<SessionDto> dtos);
}
