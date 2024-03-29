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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(SessionCreateDto session)
    {
        if (session == null)
        {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_EMPTY);
        }

        log.debug("Creating session for event {}", session.getEventId());

        var obj = Session.builder()
                .title(session.getTitle())
                .eventId(session.getEventId())
                .start(session.getStart())
                .end(session.getEnd())
                .speaker(session.getSpeaker())
                .location(session.getLocation())
                .build();

        var res = repository.save(obj);

        userSessionRepository.recordChanging(
                session.getUserId(),
                res.getId(),
                session.getStatus()
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public SessionDto findById(UUID id)
    {
        log.debug("Looking for session with id {}", id);

        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND))
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeStartTime(UUID id, Timestamp newStart)
    {
        log.debug("Changing start time for session with id {}", id);

        if (sessionIsNotExists(id))
        {
            throw new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND);
        }

        repository.changeStart(id, newStart);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeEndTime(UUID id, Timestamp newEnd)
    {
        log.debug("Changing end time for session with id {}", id);

        if (sessionIsNotExists(id))
        {
            throw new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND);
        }

        repository.changeEnd(id, newEnd);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeSpeaker(UUID id, String newSpeaker)
    {
        log.debug("Changing speaker for session with id {}", id);

        if (sessionIsNotExists(id))
        {
            throw new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND);
        }

        repository.changeSpeaker(id, newSpeaker);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeLocation(UUID id, String newLocation)
    {
        log.debug("Changing location for session with id {}", id);

        if (sessionIsNotExists(id))
        {
            throw new SessionWasNotFoundException(ErrorMessage.SESSION_WAS_NOT_FOUND);
        }

        repository.changeLocation(id, newLocation);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(UUID id)
    {
        log.debug("Deleting session with id {}", id);

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
