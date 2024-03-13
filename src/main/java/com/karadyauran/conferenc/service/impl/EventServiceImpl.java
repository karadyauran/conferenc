package com.karadyauran.conferenc.service.impl;

import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.dto.normal.EventDto;
import com.karadyauran.conferenc.error.EventWasNotFoundException;
import com.karadyauran.conferenc.error.UserIdWasNotFoundException;
import com.karadyauran.conferenc.error.UserRoleIsNotMatches;
import com.karadyauran.conferenc.error.message.ErrorMessage;
import com.karadyauran.conferenc.mapper.EventCreateMapper;
import com.karadyauran.conferenc.mapper.EventMapper;
import com.karadyauran.conferenc.model.enums.Role;
import com.karadyauran.conferenc.repository.EventCategoryMappingRepository;
import com.karadyauran.conferenc.repository.EventRepository;
import com.karadyauran.conferenc.repository.UserRepository;
import com.karadyauran.conferenc.service.interf.EventService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EventServiceImpl implements EventService
{
    EventRepository repository;
    UserRepository userRepository;
    EventCategoryMappingRepository categoryMappingRepository;

    EventMapper mapper;
    EventCreateMapper createMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(EventCreateDto event)
    {
        if (event == null)
        {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_EMPTY);
        }

        if (userRepository.getUserById(event.getOrganizerId()) == Role.ATTENDEE)
        {
            throw new UserRoleIsNotMatches(ErrorMessage.USER_ROLE_IS_NOT_MATCHES);
        }

        log.debug("Creating event by organizer id {}", event.getOrganizerId());

        if (userDoesNotExists(event.getOrganizerId()))
        {
            throw new UserIdWasNotFoundException(ErrorMessage.USER_ID_WAS_NOT_FOUND);
        }

        repository.save(
                createMapper.toEntity(event)
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<EventDto> findAll()
    {
        var obg = mapper.toDtoList(
                repository.findAll()
        );

        System.out.println(obg);

        return obg;
    }

    @Override
    @Transactional
    public EventDto findById(UUID id)
    {
        log.debug("Looking for event with id {}", id);

        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND))
        );
    }

    @Override
    @Transactional
    public List<EventDto> findByUserId(UUID user)
    {
        log.debug("Looking for events for user with id {}", user);

        return mapper.toDtoList(
                repository.findAllByOrganizerId(user)
                        .orElse(null)
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addExistCategory(UUID id, UUID category)
    {
        log.debug("Adding category {} to event {}", category, id);

        categoryMappingRepository.recordChanging(id, category);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeOrganizer(UUID id, UUID newOrganizer)
    {
        log.debug("Changing organizer for event with id {}", id);

        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeOrganizer(id, newOrganizer);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeTitle(UUID id, String newTitle)
    {
        log.debug("Changing title for event with id {}", id);

        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeTitle(id, newTitle);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeDescription(UUID id, String newDescription)
    {
        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeDescription(id, newDescription);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeStartTime(UUID id, Timestamp newStart)
    {
        log.debug("Changing start time for event with id {}", id);

        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeStart(id, newStart);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeEndTime(UUID id, Timestamp newEnd)
    {
        log.debug("Changing end time for event with id {}", id);

        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeEnd(id, newEnd);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeLocation(UUID id, String newLocation)
    {
        log.debug("Changing location for event with id {}", id);

        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeLocation(id, newLocation);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeCapacity(UUID id, Integer newCapacity)
    {
        log.debug("Changing capacity for event with id {}", id);

        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeCapacity(id, newCapacity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(UUID id)
    {
        log.debug("Deleting event with id {}", id);

        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.deleteById(id);
    }

    @Override
    public boolean eventDoesNotExists(UUID id)
    {
        return !repository.existsById(id);
    }

    @Override
    public boolean userDoesNotExists(UUID id)
    {
        return !userRepository.existsById(id);
    }
}
