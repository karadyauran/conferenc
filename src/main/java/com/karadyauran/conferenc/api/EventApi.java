package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.dto.normal.EventDto;
import com.karadyauran.conferenc.model.enums.Status;
import com.karadyauran.conferenc.validation.interf.Uuid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/event/")
public interface EventApi
{
    @PostMapping("/append/")
    ResponseEntity<String> create(@RequestBody EventCreateDto event);

    @GetMapping("/find/id/")
    ResponseEntity<EventDto> findById(@Uuid @RequestParam UUID id);

    @GetMapping("/find/user/")
    ResponseEntity<List<EventDto>> findByUserId(@Uuid @RequestParam UUID user);

    @PutMapping("/change/title")
    ResponseEntity<String> changeTitle(@Uuid @RequestParam UUID id, @RequestParam String newTitle);

    @PutMapping("/change/description")
    ResponseEntity<String> changeDescription(@Uuid @RequestParam UUID id, @RequestParam String newDescription);

    @PutMapping("/change/time/start")
    ResponseEntity<String> changeStartTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newStart);

    @PutMapping("/change/time/end")
    ResponseEntity<String> changeEndTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newEnd);

    @PutMapping("/change/location")
    ResponseEntity<String> changeLocation(@Uuid @RequestParam UUID id, @RequestParam String newLocation);

    @PutMapping("/change/capacity")
    ResponseEntity<String> changeCapacity(@Uuid @RequestParam UUID id, @RequestParam Integer newCapacity);

    @DeleteMapping("/delete/")
    ResponseEntity<String> delete(@Uuid @RequestParam UUID id);
}
