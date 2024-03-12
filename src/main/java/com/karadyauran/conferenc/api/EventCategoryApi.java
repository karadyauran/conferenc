package com.karadyauran.conferenc.api;

import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import com.karadyauran.conferenc.dto.shorted.EventCategoryShortDto;
import com.karadyauran.conferenc.validation.interf.Uuid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface EventCategoryApi
{
    @PostMapping("/create")
    ResponseEntity<String> create(@RequestBody EventCategoryCreateDto category);

    @GetMapping("/find/id")
    ResponseEntity<EventCategoryShortDto> findById(@Uuid @RequestParam UUID id);

    @PutMapping("/change/name")
    ResponseEntity<String> changeName(@Uuid @RequestParam UUID id, @RequestParam String newName);

    @PutMapping("/change/description")
    ResponseEntity<String> changeDescription(@Uuid @RequestParam UUID id, @RequestParam String newDescription);

    @DeleteMapping("/delete")
    ResponseEntity<String> delete(@Uuid @RequestParam UUID id);
}
