package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.EventCategoryDto;
import com.karadyauran.conferenc.model.EventCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventCategoryMapper
{
    EventCategoryDto toDto(EventCategory event);

    List<EventCategoryDto> toDtoList(List<EventCategory> events);

    EventCategory toEntity(EventCategoryDto dto);

    List<EventCategory> toEntityList(List<EventCategoryDto> dtos);
}
