package com.karadyauran.conferenc.service.interf;

import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.dto.normal.EventDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface EventService
{
    void create(EventCreateDto event);

    List<EventDto> findAll();

    EventDto findById(UUID id);

    List<EventDto> findByUserId(UUID user);

    List<EventDto> findByLocation(String location);

    List<EventDto> findByTitle(String title);

    void addExistCategory(UUID id, UUID category);

    void changeOrganizer(UUID id, UUID newOrganizer);

    void changeTitle(UUID id, String newTitle);

    void changeDescription(UUID id, String newSpeaker);

    void changeStartTime(UUID id, Timestamp newStart);

    void changeEndTime(UUID id, Timestamp newEnd);

    void changeLocation(UUID id, String newLocation);

    void changeCapacity(UUID id, Integer newLocation);

    void delete(UUID id);

    boolean eventDoesNotExists(UUID id);

    boolean userDoesNotExists(UUID id);
}
