package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.BookingCreateDto;
import com.karadyauran.conferenc.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingCreateMapper
{
    @Mappings({
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "eventId", source = "eventId"),
    })
    BookingCreateDto toDto(Booking booking);

    @Mappings({
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "eventId", source = "eventId"),
    })
    List<BookingCreateDto> toDtoList(List<Booking> bookings);

    @Mappings({
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "eventId", source = "eventId"),
    })
    Booking toEntity(BookingCreateDto dto);

    @Mappings({
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "eventId", source = "eventId"),
    })
    List<Booking> toEntityList(List<BookingCreateDto> dtos);
}
