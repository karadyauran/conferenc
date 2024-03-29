package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.dto.normal.EventDto;
import com.karadyauran.conferenc.validation.interf.Uuid;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/api/event/find/location")
    ResponseEntity<List<EventDto>> findByLocation(@RequestParam String location);

    @GetMapping("/api/event/find/title")
    ResponseEntity<List<EventDto>> findByTitle(@RequestParam String title);

    @PostMapping("/api/event/add-category")
    ResponseEntity<Void> addCategory(@RequestParam UUID id, @RequestParam UUID categoryId);

    @PutMapping("/api/event/change/title")
    ResponseEntity<String> changeTitle(@Uuid @RequestParam UUID id, @RequestParam String newTitle);

    @PutMapping("/api/event/change/description")
    ResponseEntity<String> changeDescription(@Uuid @RequestParam UUID id, @RequestParam String newDescription);

    @PutMapping("/api/event/change/time/start")
    ResponseEntity<String> changeStartTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newStart);

    @PutMapping("/api/event/change/time/end")
    ResponseEntity<String> changeEndTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newEnd);

    @PutMapping("/api/event/change/location")
    ResponseEntity<String> changeLocation(@Uuid @RequestParam UUID id, @RequestParam String newLocation);

    @PutMapping("/api/event/change/capacity")
    ResponseEntity<String> changeCapacity(@Uuid @RequestParam UUID id, @RequestParam Integer newCapacity);

    @DeleteMapping("/api/event/delete")
    ResponseEntity<String> delete(@Uuid @RequestParam UUID id);
}
