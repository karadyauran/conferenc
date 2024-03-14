package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.EventCategoryDto;
import com.karadyauran.conferenc.model.EventCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventCategoryMapper
{
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
    })
    EventCategoryDto toDto(EventCategory event);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
    })
    List<EventCategoryDto> toDtoList(List<EventCategory> events);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
    })
    EventCategory toEntity(EventCategoryDto dto);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
    })
    List<EventCategory> toEntityList(List<EventCategoryDto> dtos);
}
