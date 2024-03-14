package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventCreateMapper
{
    @Mappings({
            @Mapping(target = "organizerId", source = "organizerId"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic"),
    })
    EventCreateDto toDto(Event event);

    @Mappings({
            @Mapping(target = "organizerId", source = "organizerId"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic"),
    })
    List<EventCreateDto> toDtoList(List<Event> events);

    @Mappings({
            @Mapping(target = "organizerId", source = "organizerId"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic"),
    })
    Event toEntity(EventCreateDto dto);

    @Mappings({
            @Mapping(target = "organizerId", source = "organizerId"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic"),
    })
    List<Event> toEntityList(List<EventCreateDto> dtos);
}
