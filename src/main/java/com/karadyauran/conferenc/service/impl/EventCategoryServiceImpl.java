package com.karadyauran.conferenc.service.impl;

import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import com.karadyauran.conferenc.dto.normal.EventCategoryDto;
import com.karadyauran.conferenc.error.EventCategoryWasNotFoundException;
import com.karadyauran.conferenc.error.message.ErrorMessage;
import com.karadyauran.conferenc.mapper.EventCategoryCreateMapper;
import com.karadyauran.conferenc.mapper.EventCategoryMapper;
import com.karadyauran.conferenc.mapper.EventCategoryShortMapper;
import com.karadyauran.conferenc.repository.EventCategoryRepository;
import com.karadyauran.conferenc.service.interf.EventCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EventCategoryServiceImpl implements EventCategoryService
{
    EventCategoryRepository repository;

    EventCategoryMapper mapper;
    EventCategoryCreateMapper createMapper;
    EventCategoryShortMapper shortMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(EventCategoryCreateDto category)
    {
        if (category == null)
        {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_EMPTY);
        }

        log.debug("Creating category with name {}", category.getName());

        repository.save(
                createMapper.toEntity(category)
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public EventCategoryDto findById(UUID id)
    {
        log.debug("Looking for event category with id {}", id);

        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new EventCategoryWasNotFoundException(ErrorMessage.EVENT_CATEGORY_WAS_NOT_FOUND))
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeName(UUID id, String newName)
    {
        log.debug("Changing name for event category with id {}", id);

        if (eventCategoryDoesNotExists(id))
        {
            throw new EventCategoryWasNotFoundException(ErrorMessage.EVENT_CATEGORY_WAS_NOT_FOUND);
        }

        repository.changeName(id, newName);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeDescription(UUID id, String newDescription)
    {
        log.debug("Changing description for event category with id {}", id);

        if (eventCategoryDoesNotExists(id))
        {
            throw new EventCategoryWasNotFoundException(ErrorMessage.EVENT_CATEGORY_WAS_NOT_FOUND);
        }

        repository.changeDescription(id, newDescription);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(UUID id)
    {
        log.debug("Deleting event category with id {}", id);

        if (eventCategoryDoesNotExists(id))
        {
            throw new EventCategoryWasNotFoundException(ErrorMessage.EVENT_CATEGORY_WAS_NOT_FOUND);
        }

        repository.deleteById(id);
    }

    @Override
    public boolean eventCategoryDoesNotExists(UUID id)
    {
        return !repository.existsById(id);
    }
}
