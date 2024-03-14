package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.shorted.EventShortDto;
import com.karadyauran.conferenc.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventShortMapper
{
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic")
    })
    EventShortDto toDto(Event event);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic")
    })
    List<EventShortDto> toDtoList(List<Event> events);
}
