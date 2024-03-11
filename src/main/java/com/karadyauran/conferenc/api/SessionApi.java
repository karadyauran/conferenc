package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.SessionCreateDto;
import com.karadyauran.conferenc.dto.normal.SessionDto;
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
import java.util.UUID;

@RequestMapping("/api/session/")
public interface SessionApi
{
    @PostMapping("/append/")
    ResponseEntity<Void> create(@RequestBody SessionCreateDto session);

    @GetMapping("/find/id/")
    ResponseEntity<SessionDto> findById(@Uuid @RequestParam UUID id);

    @PutMapping("/change/title")
    ResponseEntity<Void> changeTitle(@Uuid @RequestParam UUID id, @RequestParam String newTitle);

    @PutMapping("/change/time/start")
    ResponseEntity<Void> changeStartTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newStart);

    @PutMapping("/change/time/end")
    ResponseEntity<Void> changeEndTime(@Uuid @RequestParam UUID id, @RequestParam Timestamp newEnd);

    @PutMapping("/change/speaker")
    ResponseEntity<Void> changeSpeaker(@Uuid @RequestParam UUID id, @RequestParam String newSpeaker);

    @PutMapping("/change/location")
    ResponseEntity<Void> changeLocation(@Uuid @RequestParam UUID id, @RequestParam String newLocation);

    @DeleteMapping("/delete/")
    ResponseEntity<Void> delete(@Uuid @RequestParam UUID id);
}
