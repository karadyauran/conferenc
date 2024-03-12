package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventCreateMapper
{
    EventCreateDto toDto(Event event);

    List<EventCreateDto> toDtoList(List<Event> events);

    Event toEntity(EventCreateDto dto);

    List<Event> toEntityList(List<EventCreateDto> dtos);
}
