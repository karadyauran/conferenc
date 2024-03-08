package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.EventDto;
import com.karadyauran.conferenc.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper
{
    EventDto toDto(Event event);

    List<EventDto> toDtoList(List<Event> events);

    Event toEntity(EventDto dto);

    List<Event> toEntityList(List<EventDto> dtos);
}
