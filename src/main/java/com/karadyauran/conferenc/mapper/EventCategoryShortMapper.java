package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.shorted.EventCategoryShortDto;
import com.karadyauran.conferenc.model.EventCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventCategoryShortMapper
{
    @Mapping(target = "name", source = "name")
    EventCategoryShortDto toDto(EventCategory event);

    @Mapping(target = "name", source = "name")
    List<EventCategoryShortDto> toDtoList(List<EventCategory> events);
}
