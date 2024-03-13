package com.karadyauran.conferenc.service.interf;

import com.karadyauran.conferenc.dto.create.BookingCreateDto;
import com.karadyauran.conferenc.dto.normal.BookingDto;
import com.karadyauran.conferenc.model.enums.Status;

import java.util.List;
import java.util.UUID;

public interface BookingService
{
    void create(BookingCreateDto booking);

    BookingDto findById(UUID id);

    List<BookingDto> findByUserId(UUID id);

    void changeStatus(UUID id, Status newStatus);

    void delete(UUID id);

    boolean bookingDoesNotExists(UUID id);

    boolean userDoesNotExists(UUID id);

    boolean eventDoesNotExists(UUID id);
}
