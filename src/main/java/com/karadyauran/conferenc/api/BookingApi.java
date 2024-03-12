package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.BookingCreateDto;
import com.karadyauran.conferenc.dto.normal.BookingDto;
import com.karadyauran.conferenc.model.enums.Status;
import com.karadyauran.conferenc.validation.interf.Uuid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface BookingApi
{
    @PostMapping("/api/booking/create")
    ResponseEntity<String> create(@RequestBody BookingCreateDto booking);

    @GetMapping("/api/booking/find/id")
    ResponseEntity<BookingDto> findById(@Uuid @RequestParam UUID id);

    @GetMapping("/api/booking/find/user")
    ResponseEntity<List<BookingDto>> findByUserId(@Uuid @RequestParam UUID user);

    @PutMapping("/api/booking/change/status")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> changeStatus(@Uuid @RequestParam UUID id, @RequestParam Status newStatus);

    @DeleteMapping("/api/booking/delete")
    @PreAuthorize("isAuthenticated() and @userServiceImpl.isProfileOwner(authentication, #id)")
    ResponseEntity<String> delete(@Uuid @RequestParam UUID id);
}
