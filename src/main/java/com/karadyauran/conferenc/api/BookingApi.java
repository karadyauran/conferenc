package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.BookingCreateDto;
import com.karadyauran.conferenc.dto.normal.BookingDto;
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

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/booking/")
public interface BookingApi
{
    @PostMapping("/create")
    ResponseEntity<String> create(@RequestBody BookingCreateDto booking);

    @GetMapping("/find/id")
    ResponseEntity<BookingDto> findById(@Uuid @RequestParam UUID id);

    @GetMapping("/find/user")
    ResponseEntity<List<BookingDto>> findByUserId(@Uuid @RequestParam UUID user);

    @PutMapping("/change/status")
    ResponseEntity<String> changeStatus(@Uuid @RequestParam UUID id, @RequestParam Status newStatus);

    @DeleteMapping("/delete")
    ResponseEntity<String> delete(@Uuid @RequestParam UUID id);
}
