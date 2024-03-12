package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.SessionCreateDto;
import com.karadyauran.conferenc.dto.normal.SessionDto;
import com.karadyauran.conferenc.validation.interf.Uuid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.UUID;

public interface SessionApi
{
    @PostMapping("/api/session/create")
    ResponseEntity<String> create(@RequestBody SessionCreateDto session);

    @GetMapping("/api/session/find/id")
    ResponseEntity<SessionDto> findById(@Uuid @RequestParam UUID id);

    @PutMapping("/api/session/change/time/start")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeStartTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newStart);

    @PutMapping("/api/session/change/time/end")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeEndTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newEnd);

    @PutMapping("/api/session/change/speaker")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeSpeaker(@Uuid @RequestParam UUID id, @RequestParam String newSpeaker);

    @PutMapping("/api/session/change/location")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeLocation(@Uuid @RequestParam UUID id, @RequestParam String newLocation);

    @DeleteMapping("/api/session/delete")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> delete(@Uuid @RequestParam UUID id);
}
