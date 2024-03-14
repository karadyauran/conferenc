package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import com.karadyauran.conferenc.model.EventCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventCategoryCreateMapper
{
    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
    })
    EventCategoryCreateDto toDto(EventCategory event);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
    })
    List<EventCategoryCreateDto> toDtoList(List<EventCategory> events);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
    })
    EventCategory toEntity(EventCategoryCreateDto dto);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
    })
    List<EventCategory> toEntityList(List<EventCategoryCreateDto> dtos);
}
