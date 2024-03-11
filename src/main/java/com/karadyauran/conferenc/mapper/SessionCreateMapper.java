package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.SessionCreateDto;
import com.karadyauran.conferenc.dto.normal.SessionDto;
import com.karadyauran.conferenc.model.Session;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionCreateMapper
{
    SessionCreateDto toDto(Session session);

    List<SessionCreateDto> toDtoList(List<Session> sessions);

    Session toEntity(SessionCreateDto dto);

    List<Session> toEntityList(List<SessionCreateDto> dtos);
}
