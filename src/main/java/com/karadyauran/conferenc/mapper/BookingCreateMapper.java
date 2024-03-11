package com.karadyauran.conferenc.mapper;

import com.karadyauran.conferenc.dto.create.BookingCreateDto;
import com.karadyauran.conferenc.model.Booking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingCreateMapper
{
    BookingCreateDto toDto(Booking booking);

    List<BookingCreateDto> toDtoList(List<Booking> bookings);

    Booking toEntity(BookingCreateDto dto);

    List<Booking> toEntityList(List<BookingCreateDto> dtos);
}
