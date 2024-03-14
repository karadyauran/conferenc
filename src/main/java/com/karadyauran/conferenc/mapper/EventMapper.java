package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.EventDto;
import com.karadyauran.conferenc.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper
{
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "organizer", source = "organizer"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic"),
            @Mapping(target = "session", source = "session"),
            @Mapping(target = "bookings", source = "bookings"),
            @Mapping(target = "categories", source = "categories"),
    })
    EventDto toDto(Event event);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "organizer", source = "organizer"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic"),
            @Mapping(target = "session", source = "session"),
            @Mapping(target = "bookings", source = "bookings"),
            @Mapping(target = "categories", source = "categories"),
    })
    List<EventDto> toDtoList(List<Event> events);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "organizer", source = "organizer"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic"),
            @Mapping(target = "session", source = "session"),
            @Mapping(target = "bookings", source = "bookings"),
            @Mapping(target = "categories", source = "categories"),
    })
    Event toEntity(EventDto dto);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "organizer", source = "organizer"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "end", source = "end"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "capacity", source = "capacity"),
            @Mapping(target = "isPublic", source = "isPublic"),
            @Mapping(target = "session", source = "session"),
            @Mapping(target = "bookings", source = "bookings"),
            @Mapping(target = "categories", source = "categories"),
    })
    List<Event> toEntityList(List<EventDto> dtos);
}
