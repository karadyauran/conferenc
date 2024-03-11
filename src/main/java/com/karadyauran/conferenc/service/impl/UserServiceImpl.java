package com.karadyauran.conferenc.service.impl;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.dto.shorted.UserShortDto;
import com.karadyauran.conferenc.error.UserIdWasNotFoundException;
import com.karadyauran.conferenc.error.UsernameIsAlreadyExistsException;
import com.karadyauran.conferenc.error.message.ErrorMessage;
import com.karadyauran.conferenc.mapper.UserShortMapper;
import com.karadyauran.conferenc.model.User;
import com.karadyauran.conferenc.model.enums.Role;
import com.karadyauran.conferenc.repository.UserRepository;
import com.karadyauran.conferenc.service.interf.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService
{
    PasswordEncoder passwordEncoder;

    UserRepository repository;

    UserShortMapper shortMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(UserCreateDto user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_EMPTY);
        }

        log.debug("Creating user with username {}", user.getUsername());

        if (userAlreadyExists(user.getUsername()))
        {
            throw new UsernameIsAlreadyExistsException(ErrorMessage.USERNAME_IS_ALREADY_EXISTS);
        }

        var obj = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(
                        passwordEncoder.encode(user.getPassword())
                )
                .role(Role.valueOf(user.getRole()))
                .build();

        repository.save(obj);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserShortDto findById(UUID id)
    {
        log.debug("Looking for the user with id {}", id);

        return shortMapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new UserIdWasNotFoundException(ErrorMessage.USER_ID_WAS_NOT_FOUND))
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserShortDto findByUsername(String username)
    {
        log.debug("Looking for the user with username {}", username);

        return shortMapper.toDto(
                repository.findUserByUsername(username)
                        .orElseThrow(() -> new UserIdWasNotFoundException(ErrorMessage.USERNAME_WAS_NOT_FOUND))
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changeUsername(UUID id, String username)
    {
        log.debug("Changing user's username on {}", username);

        if (userDoesNotExistsById(id))
        {
            throw new UserIdWasNotFoundException(ErrorMessage.USER_ID_WAS_NOT_FOUND);
        }

        if (userAlreadyExists(username))
        {
            throw new UsernameIsAlreadyExistsException(ErrorMessage.USERNAME_IS_ALREADY_EXISTS);
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(UUID id)
    {
        log.debug("Deleting user with id {}", id);

        if (userDoesNotExistsById(id))
        {
            throw new UserIdWasNotFoundException(ErrorMessage.USER_ID_WAS_NOT_FOUND);
        }

        repository.deleteById(id);
    }

    @Override
    public boolean userDoesNotExistsById(UUID id)
    {
        return !repository.existsById(id);
    }

    @Override
    public boolean userDoesNotExistsByUsername(String username)
    {
        return !repository.existsByUsername(username);
    }

    @Override
    public boolean userAlreadyExists(String username)
    {
        return repository.existsByUsername(username);
    }
}
