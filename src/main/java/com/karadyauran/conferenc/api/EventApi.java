package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.dto.normal.EventDto;
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
import java.util.List;
import java.util.UUID;

public interface EventApi
{
    @PostMapping("/api/event/create")
    ResponseEntity<String> create(@RequestBody EventCreateDto event);

    @GetMapping("/api/event/all")
    ResponseEntity<List<EventDto>> findAll();

    @GetMapping("/api/event/find/id")
    ResponseEntity<EventDto> findById(@Uuid @RequestParam UUID id);

    @GetMapping("/api/event/find/user")
    ResponseEntity<List<EventDto>> findByUserId(@Uuid @RequestParam UUID user);

    @PostMapping("/api/event/add-category")
    ResponseEntity<Void> addCategory(@RequestParam UUID id, @RequestParam UUID categoryId);

    @PutMapping("/api/event/change/title")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeTitle(@Uuid @RequestParam UUID id, @RequestParam String newTitle);

    @PutMapping("/api/event/change/description")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeDescription(@Uuid @RequestParam UUID id, @RequestParam String newDescription);

    @PutMapping("/api/event/change/time/start")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeStartTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newStart);

    @PutMapping("/api/event/change/time/end")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeEndTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newEnd);

    @PutMapping("/api/event/change/location")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeLocation(@Uuid @RequestParam UUID id, @RequestParam String newLocation);

    @PutMapping("/api/event/change/capacity")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeCapacity(@Uuid @RequestParam UUID id, @RequestParam Integer newCapacity);

    @DeleteMapping("/api/event/delete")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> delete(@Uuid @RequestParam UUID id);
}
