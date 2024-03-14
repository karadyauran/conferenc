package com.karadyauran.conferenc.service.impl;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.dto.normal.UserDto;
import com.karadyauran.conferenc.error.EmailIsAlreadyTakenException;
import com.karadyauran.conferenc.error.UserIdWasNotFoundException;
import com.karadyauran.conferenc.error.UserRoleIsNotMatches;
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
    static String ADMIN_KEY = "9D5B90F1C8DD7745F36E5B7C635682C4ED5D0AA624FCE5B7492B2A5F6B4E517C";
    PasswordEncoder passwordEncoder;
    UserRepository repository;
    UserMapper mapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(UserCreateDto userDto)
    {
        validateUserCreateDto(userDto);
        log.debug("Creating user with username {}", userDto.getUsername());
        ensureUniqueUsernameAndEmail(userDto.getUsername(), userDto.getEmail());
        ensureValidUserRole(userDto.getRole());

        User newUser = buildUserFromDto(userDto);
        repository.save(newUser);
    }

    private void validateUserCreateDto(UserCreateDto userDto)
    {
        if (userDto == null)
        {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_EMPTY);
        }
    }

    private void ensureUniqueUsernameAndEmail(String username, String email)
    {
        if (userAlreadyExists(username))
        {
            throw new UsernameIsAlreadyExistsException(ErrorMessage.USERNAME_IS_ALREADY_EXISTS);
        }
        if (emailIsAlreadyTaken(email))
        {
            throw new EmailIsAlreadyTakenException(ErrorMessage.EMAIL_IS_ALREADY_TAKEN);
        }
    }

    private void ensureValidUserRole(String userRole)
    {
        if ("ADMIN".equals(userRole))
        {
            throw new UserRoleIsNotMatches(ErrorMessage.USER_ROLE_IS_NOT_MATCHES);
        }
    }

    private User buildUserFromDto(UserCreateDto userDto)
    {
        Role role = determineUserRole(userDto.getRole());
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(role)
                .build();
    }

    private Role determineUserRole(String userRole)
    {
        if (ADMIN_KEY.equals(userRole))
        {
            return Role.ADMIN;
        } else
        {
            return Role.valueOf(userRole);
        }
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
}
