package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.dto.normal.UserDto;
import com.karadyauran.conferenc.validation.interf.Uuid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RequestMapping("/api/user")
public interface UserApi
{
    @PostMapping("/register")
    ResponseEntity<String> create(@RequestBody UserCreateDto user);

    @GetMapping("/")
    ResponseEntity<UserDto> findById(@RequestParam UUID id);

    @GetMapping("/{username}")
    ResponseEntity<UserDto> findByUsername(@PathVariable String username);

    @PutMapping("/change/username/")
    ResponseEntity<String> changeUsername(@Uuid @RequestParam UUID id, @RequestParam String username);

    @DeleteMapping("/delete")
    ResponseEntity<String> deleteById(@Uuid @RequestParam UUID id);
}
