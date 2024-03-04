package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.dto.shorted.UserShortDto;
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

@RequestMapping("/api/user/")
public interface UserApi
{
    @PostMapping("/append/")
    ResponseEntity<Void> create(@RequestBody UserCreateDto user);

    @GetMapping("/{username}")
    ResponseEntity<UserShortDto> findByUsername(@PathVariable String username);

    @PutMapping("/change/username/")
    ResponseEntity<UserShortDto> changeUsername(@RequestParam UUID id, @RequestParam String username);

    @DeleteMapping("/delete/")
    ResponseEntity<Void> deleteById(@RequestParam UUID id);
}
