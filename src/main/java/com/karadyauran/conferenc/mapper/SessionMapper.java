package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.SessionDto;
import com.karadyauran.conferenc.model.Session;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper
{
    SessionDto toDto(Session session);

    List<SessionDto> toDtoList(List<Session> sessions);

    Session toEntity(SessionDto dto);

    List<Session> toEntityList(List<SessionDto> dtos);
}
