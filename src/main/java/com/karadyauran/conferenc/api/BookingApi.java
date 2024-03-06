package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.normal.BookingDto;
import com.karadyauran.conferenc.model.enums.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/booking/")
public interface BookingApi
{
    @PostMapping("/append/")
    ResponseEntity<Void> create(@RequestParam UUID userId, @RequestParam UUID eventId, @RequestParam Status status);

    @GetMapping("/find/id/")
    ResponseEntity<BookingDto> findById(@RequestParam UUID id);

    @GetMapping("/find/user/")
    ResponseEntity<List<BookingDto>> findByUserId(@RequestParam UUID user);

    @PutMapping("/change/status")
    ResponseEntity<Void> changeStatus(@RequestParam UUID id, @RequestParam Status newStatus);

    @DeleteMapping("/delete/")
    ResponseEntity<Void> delete(@RequestParam UUID id);
}