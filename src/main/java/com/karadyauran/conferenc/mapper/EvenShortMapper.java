package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.shorted.EventShortDto;
import com.karadyauran.conferenc.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EvenShortMapper
{
    EventShortDto toDto(Event event);

    List<EventShortDto> toDtoList(List<Event> events);

    Event toEntity(EventShortDto dto);

    List<Event> toEntityList(List<EventShortDto> dtos);
}
