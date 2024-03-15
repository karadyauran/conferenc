package com.karadyauran.conferenc.service.impl;

import com.karadyauran.conferenc.dto.create.BookingCreateDto;
import com.karadyauran.conferenc.dto.normal.BookingDto;
import com.karadyauran.conferenc.error.AlreadyBookedException;
import com.karadyauran.conferenc.error.BookingWasNotFoundException;
import com.karadyauran.conferenc.error.CapacityLimitException;
import com.karadyauran.conferenc.error.EventWasNotFoundException;
import com.karadyauran.conferenc.error.UserIdWasNotFoundException;
import com.karadyauran.conferenc.error.UserRoleIsNotMatches;
import com.karadyauran.conferenc.error.message.ErrorMessage;
import com.karadyauran.conferenc.mapper.BookingCreateMapper;
import com.karadyauran.conferenc.mapper.BookingMapper;
import com.karadyauran.conferenc.model.enums.Role;
import com.karadyauran.conferenc.model.enums.Status;
import com.karadyauran.conferenc.repository.BookingRepository;
import com.karadyauran.conferenc.repository.EventRepository;
import com.karadyauran.conferenc.repository.UserRepository;
import com.karadyauran.conferenc.service.interf.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookingServiceImpl implements BookingService
{
    BookingRepository repository;
    UserRepository userRepository;
    EventRepository eventRepository;

    BookingMapper mapper;
    BookingCreateMapper createMapper;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(BookingCreateDto booking)
    {
        if (validateBooking(booking))
        {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_EMPTY);
        }

        UUID eventId = booking.getEventId();
        UUID userId = booking.getUserId();

        if (validateUserAndRole(userId))
        {
            throw new UserRoleIsNotMatches(ErrorMessage.USER_ROLE_IS_NOT_MATCHES);
        }

        if (eventDoesNotExists(eventId))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        if (userDoesNotExists(userId))
        {
            throw new UserIdWasNotFoundException(ErrorMessage.USER_ID_WAS_NOT_FOUND);
        }

        if (checkCapacity(eventId))
        {
            throw new CapacityLimitException(ErrorMessage.CAPACITY_LIMIT);
        }

        if (checkExistence(eventId, userId))
        {
            throw new AlreadyBookedException(ErrorMessage.ALREADY_BOOKED);
        }

        int currCapacity = repository.findLastBookingCapacityForEvent(eventId).orElse(0);
        saveBooking(booking, currCapacity);
    }

    private boolean validateBooking(BookingCreateDto booking)
    {
        return booking == null;
    }

    private boolean validateUserAndRole(UUID userId)
    {
        return userRepository.getUserById(userId) == Role.ORGANIZER;
    }

    private boolean checkCapacity(UUID eventId)
    {
        int currCapacity = repository.findLastBookingCapacityForEvent(eventId).orElse(0);
        int capacity = eventRepository.getEventCapacityByEventId(eventId);

        return currCapacity >= capacity;
    }

    private boolean checkExistence(UUID eventId, UUID userId)
    {
        System.out.println(repository.getBookingByEventIdAndUserId(eventId, userId));
        return repository.getBookingByEventIdAndUserId(eventId, userId).isPresent();
    }

    private void saveBooking(BookingCreateDto booking, int currCapacity)
    {
        var bookEntity = createMapper.toEntity(booking);
        bookEntity.setNumberOfAttendees(currCapacity + 1);
        log.debug("Saving booking: {}", bookEntity);
        repository.save(bookEntity);
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BookingDto findById(UUID id)
    {
        log.debug("Looking for booking with id {}", id);

        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new BookingWasNotFoundException(ErrorMessage.BOOKING_WAS_NOT_FOUND))
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<BookingDto> findByUserId(UUID id)
    {
        log.debug("Looking for bookings for user with id {}", id);

        if (userDoesNotExists(id))
        {
            throw new UserIdWasNotFoundException(ErrorMessage.USER_ID_WAS_NOT_FOUND);
        }

        return mapper.toDtoList(
                repository.findAllByUserId(id)
                        .orElse(null)
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeStatus(UUID id, Status newStatus)
    {
        log.debug("Changing status for booking with id {}", id);

        if (bookingDoesNotExists(id))
        {
            throw new BookingWasNotFoundException(ErrorMessage.BOOKING_WAS_NOT_FOUND);
        }

        repository.changeStatus(id, newStatus);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(UUID id)
    {
        log.debug("Deleting booking with id {}", id);

        if (bookingDoesNotExists(id))
        {
            throw new BookingWasNotFoundException(ErrorMessage.BOOKING_WAS_NOT_FOUND);
        }

        repository.deleteById(id);
    }

    @Override
    public boolean bookingDoesNotExists(UUID id)
    {
        return !repository.existsById(id);
    }

    @Override
    public boolean userDoesNotExists(UUID id)
    {
        return !userRepository.existsById(id);
    }

    @Override
    public boolean eventDoesNotExists(UUID id)
    {
        return !eventRepository.existsById(id);
    }
}
