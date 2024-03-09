package com.karadyauran.conferenc.service.interf;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.dto.shorted.UserShortDto;

import java.util.UUID;

public interface UserService
{
    void create(UserCreateDto user);

    UserShortDto findByUsername(String username);

    void changeUsername(String username);

    void delete(UUID id);
}
