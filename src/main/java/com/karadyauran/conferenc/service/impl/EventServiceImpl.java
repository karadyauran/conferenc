package com.karadyauran.conferenc.service.impl;

import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.dto.normal.EventDto;
import com.karadyauran.conferenc.error.EventWasNotFoundException;
import com.karadyauran.conferenc.error.UserIdWasNotFoundException;
import com.karadyauran.conferenc.error.message.ErrorMessage;
import com.karadyauran.conferenc.mapper.EvenCreateMapper;
import com.karadyauran.conferenc.mapper.EventMapper;
import com.karadyauran.conferenc.repository.EventCategoryMappingRepository;
import com.karadyauran.conferenc.repository.EventRepository;
import com.karadyauran.conferenc.repository.UserRepository;
import com.karadyauran.conferenc.service.interf.EventService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    EventCategoryMappingRepository categoryRepository;

    EventMapper mapper;
    EvenCreateMapper createMapper;

    @Override
    public void create(EventCreateDto event)
    {
        if (userDoesNotExists(event.getOrganizerId()))
        {
            throw new UserIdWasNotFoundException(ErrorMessage.USER_ID_WAS_NOT_FOUND);
        }

        repository.save(
                createMapper.toEntity(event)
        );
    }

    @Override
    public EventDto findById(UUID id)
    {
        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND))
        );
    }

    @Override
    public List<EventDto> findByUserId(UUID user)
    {
        return mapper.toDtoList(
                repository.findAllByOrganizerId(user)
                        .orElse(null)
        );
    }

    @Override
    public void addCategory(UUID id, UUID category)
    {
        categoryRepository.recordChanging(id, category);
    }

    @Override
    public void changeOrganizer(UUID id, UUID newOrganizer)
    {
        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeOrganizer(id, newOrganizer);
    }

    @Override
    public void changeTitle(UUID id, String newTitle)
    {
        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeTitle(id, newTitle);
    }

    @Override
    public void changeDescription(UUID id, String newDescription)
    {
        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeDescription(id, newDescription);
    }

    @Override
    public void changeStartTime(UUID id, Timestamp newStart)
    {
        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeStart(id, newStart);
    }

    @Override
    public void changeEndTime(UUID id, Timestamp newEnd)
    {
        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeEnd(id, newEnd);
    }

    @Override
    public void changeLocation(UUID id, String newLocation)
    {
        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeLocation(id, newLocation);
    }

    @Override
    public void changeCapacity(UUID id, Integer newCapacity)
    {
        if (eventDoesNotExists(id))
        {
            throw new EventWasNotFoundException(ErrorMessage.EVENT_WAS_NOT_FOUND);
        }

        repository.changeCapacity(id, newCapacity);
    }

    @Override
    public void delete(UUID id)
    {
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
