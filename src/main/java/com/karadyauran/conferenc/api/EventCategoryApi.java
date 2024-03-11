package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.normal.EventCategoryDto;
import com.karadyauran.conferenc.validation.interf.Uuid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RequestMapping("/api/event/")
public interface EventCategoryApi
{
    @PostMapping("/append/")
    ResponseEntity<Void> create(@RequestBody EventCategoryDto category);

    @GetMapping("/find/id/")
    ResponseEntity<EventCategoryDto> findById(@Uuid @RequestParam UUID id);

    @PutMapping("/change/name")
    ResponseEntity<Void> changeTitle(@Uuid @RequestParam UUID id, @RequestParam String newName);

    @DeleteMapping("/delete/")
    ResponseEntity<Void> delete(@Uuid @RequestParam UUID id);
}
