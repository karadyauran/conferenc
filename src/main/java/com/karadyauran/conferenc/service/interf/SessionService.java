package com.karadyauran.conferenc.service.interf;

import com.karadyauran.conferenc.dto.create.SessionCreateDto;
import com.karadyauran.conferenc.dto.normal.SessionDto;

import java.sql.Timestamp;
import java.util.UUID;

public interface SessionService
{
    void create(SessionCreateDto session);

    SessionDto findById(UUID id);

    void changeTitle(UUID id, String newTitle);

    void changeStartTime(UUID id, Timestamp newStart);

    void changeEndTime(UUID id, Timestamp newEnd);

    void changeSpeaker(UUID id, String newSpeaker);

    void changeLocation(UUID id, String newLocation);

    void delete(UUID id);
}
