package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.shorted.EventCategoryShortDto;
import com.karadyauran.conferenc.model.EventCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventCategoryShortMapper
{
    EventCategoryShortDto toDto(EventCategory event);

    List<EventCategoryShortDto> toDtoList(List<EventCategory> events);

    EventCategory toEntity(EventCategoryShortDto dto);

    List<EventCategory> toEntityList(List<EventCategoryShortDto> dtos);
}
