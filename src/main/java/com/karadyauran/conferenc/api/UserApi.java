package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.dto.normal.UserDto;
import com.karadyauran.conferenc.validation.interf.Uuid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface UserApi
{
    @PostMapping("/api/user/register")
    ResponseEntity<String> create(@RequestBody UserCreateDto user);

    @GetMapping("/api/user/byid")
    ResponseEntity<UserDto> findById(@RequestParam UUID id);

    @GetMapping("/api/user/username/{username}")
    ResponseEntity<UserDto> findByUsername(@PathVariable String username);

    @PutMapping("/api/user/change/username")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeUsername(@Uuid @RequestParam UUID id, @RequestParam String username);

    @DeleteMapping("/api/user/delete")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> deleteById(@Uuid @RequestParam UUID id);
}
