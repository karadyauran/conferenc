package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import com.karadyauran.conferenc.model.EventCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventCategoryCreateMapper
{
    EventCategoryCreateDto toDto(EventCategory event);

    List<EventCategoryCreateDto> toDtoList(List<EventCategory> events);

    EventCategory toEntity(EventCategoryCreateDto dto);

    List<EventCategory> toEntityList(List<EventCategoryCreateDto> dtos);
}
