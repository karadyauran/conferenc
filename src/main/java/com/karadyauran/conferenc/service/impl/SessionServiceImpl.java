package com.karadyauran.conferenc.service.impl;

import com.karadyauran.conferenc.dto.create.SessionCreateDto;
import com.karadyauran.conferenc.dto.normal.SessionDto;
import com.karadyauran.conferenc.error.SessionWasNotFoundException;
import com.karadyauran.conferenc.error.message.ErrorMessage;
import com.karadyauran.conferenc.mapper.SessionMapper;
import com.karadyauran.conferenc.model.Session;
import com.karadyauran.conferenc.repository.SessionRepository;
import com.karadyauran.conferenc.repository.UserRepository;
import com.karadyauran.conferenc.repository.UserSessionRepository;
import com.karadyauran.conferenc.service.interf.SessionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SessionServiceImpl implements SessionService
{
    SessionRepository repository;
    UserRepository userRepository;
    UserSessionRepository userSessionRepository;

    SessionMapper mapper;

    @Override
    public void create(SessionCreateDto session)
    {
        var obj = Session.builder()
                .eventId(session.getEventId())
                .start(session.getStart())
                .end(session.getEnd())
                .speaker(session.getSpeaker())
                .build();

        var res = repository.save(obj);

        userSessionRepository.recordChanging(
                session.getUserId(),
                res.getId(),
                session.getStatus()
        );
    }

    @Override
    public SessionDto findById(UUID id)
    {
        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND))
        );
    }

    @Override
    public void changeStartTime(UUID id, Timestamp newStart)
    {
        if (sessionIsNotExists(id))
        {
            throw new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND);
        }

        repository.changeStart(id, newStart);
    }

    @Override
    public void changeEndTime(UUID id, Timestamp newEnd)
    {
        if (sessionIsNotExists(id))
        {
            throw new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND);
        }

        repository.changeEnd(id, newEnd);
    }

    @Override
    public void changeSpeaker(UUID id, String newSpeaker)
    {
        if (sessionIsNotExists(id))
        {
            throw new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND);
        }

        repository.changeSpeaker(id, newSpeaker);
    }

    @Override
    public void changeLocation(UUID id, String newLocation)
    {
        if (sessionIsNotExists(id))
        {
            throw new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND);
        }

        repository.changeLocation(id, newLocation);
    }

    @Override
    public void delete(UUID id)
    {
        if (sessionIsNotExists(id))
        {
            throw new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND);
        }

        repository.deleteById(id);
    }

    @Override
    public boolean sessionIsNotExists(UUID id)
    {
        return !repository.existsById(id);
    }

    @Override
    public boolean userDoesNotExistsById(UUID id)
    {
        return !userRepository.existsById(id);
    }
}
