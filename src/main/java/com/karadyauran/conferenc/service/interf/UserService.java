package com.karadyauran.conferenc.service.interf;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.dto.normal.UserDto;

import java.util.UUID;

public interface UserService
{
    void create(UserCreateDto user);

    UserDto findById(UUID id);

    UserDto findByUsername(String username);

    void changeUsername(UUID id, String username);

    void delete(UUID id);

    boolean userDoesNotExistsById(UUID id);

    boolean userDoesNotExistsByUsername(String username);

    boolean userAlreadyExists(String username);
}
