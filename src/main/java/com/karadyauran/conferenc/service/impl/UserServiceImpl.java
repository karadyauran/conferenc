package com.karadyauran.conferenc.service.impl;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.dto.normal.UserDto;
import com.karadyauran.conferenc.error.EmailIsAlreadyTakenException;
import com.karadyauran.conferenc.error.UserIdWasNotFoundException;
import com.karadyauran.conferenc.error.UsernameIsAlreadyExistsException;
import com.karadyauran.conferenc.error.UsernameWasNotFoundException;
import com.karadyauran.conferenc.error.message.ErrorMessage;
import com.karadyauran.conferenc.mapper.UserMapper;
import com.karadyauran.conferenc.model.User;
import com.karadyauran.conferenc.model.enums.Role;
import com.karadyauran.conferenc.repository.UserRepository;
import com.karadyauran.conferenc.service.interf.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    UserMapper mapper;

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

        if (emailIsAlreadyTaken(user.getEmail()))
        {
            throw new EmailIsAlreadyTakenException(ErrorMessage.EMAIL_IS_ALREADY_TAKEN);
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
    public UserDto findById(UUID id)
    {
        log.debug("Looking for the user with id {}", id);

        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new UserIdWasNotFoundException(ErrorMessage.USER_ID_WAS_NOT_FOUND))
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserDto findByUsername(String username)
    {
        log.debug("Looking for the user with username {}", username);

        return mapper.toDto(
                repository.findUserByUsername(username)
                        .orElseThrow(() -> new UsernameWasNotFoundException(ErrorMessage.USERNAME_WAS_NOT_FOUND))
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

        repository.changeUsername(id, username);
    }

    @Override
    public void changeEmail(UUID id, String email)
    {
        if (emailIsAlreadyTaken(email))
        {
            throw new EmailIsAlreadyTakenException(ErrorMessage.EMAIL_IS_ALREADY_TAKEN);
        }

        repository.changeEmail(id, email);
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

    @Override
    public boolean emailIsAlreadyTaken(String email)
    {
        return repository.existsByEmail(email);
    }

    public boolean isProfileOwner(Authentication authentication, UUID profileId) {
        User currentUser = (User) authentication.getPrincipal();
        UUID userId = currentUser.getId();

        return userId.equals(profileId);
    }
}
