package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.BookingDto;
import com.karadyauran.conferenc.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper
{
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "numberOfAttendees", source = "numberOfAttendees"),
    })
    BookingDto toDto(Booking booking);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "numberOfAttendees", source = "numberOfAttendees"),
    })
    List<BookingDto> toDtoList(List<Booking> bookings);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "numberOfAttendees", source = "numberOfAttendees"),
    })
    Booking toEntity(BookingDto dto);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "numberOfAttendees", source = "numberOfAttendees"),
    })
    List<Booking> toEntityList(List<BookingDto> dtos);
}
