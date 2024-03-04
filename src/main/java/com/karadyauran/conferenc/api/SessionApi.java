package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.normal.SessionDto;
import com.karadyauran.conferenc.model.enums.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.UUID;

@RequestMapping("/api/session/")
public interface SessionApi
{
    @PostMapping("/append/")
    ResponseEntity<Void> create(@RequestParam UUID userId, @RequestParam UUID eventId, @RequestParam Status status);

    @GetMapping("/find/id/")
    ResponseEntity<SessionDto> findById(@RequestParam UUID id);

    @PutMapping("/change/title")
    ResponseEntity<Void> changeTitle(@RequestParam UUID id, @RequestParam Status newTitle);

    @PutMapping("/change/time/start")
    ResponseEntity<Void> changeStartTime(@RequestParam UUID id, @RequestParam Timestamp newStart);

    @PutMapping("/change/time/end")
    ResponseEntity<Void> changeEndTime(@RequestParam UUID id, @RequestParam Timestamp newEnd);

    @PutMapping("/change/speaker")
    ResponseEntity<Void> changeSpeaker(@RequestParam UUID id, @RequestParam String newSpeaker);

    @PutMapping("/change/location")
    ResponseEntity<Void> changeLocation(@RequestParam UUID id, @RequestParam String newLocation);

    @DeleteMapping("/delete/")
    ResponseEntity<Void> delete(@RequestParam UUID id);
}
