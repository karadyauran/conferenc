package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.normal.BookingDto;
import com.karadyauran.conferenc.model.Booking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper
{
    BookingDto toDto(Booking booking);

    List<BookingDto> toDtoList(List<Booking> bookings);

    Booking toEntity(BookingDto dto);

    List<Booking> toEntityList(List<BookingDto> dtos);
}
