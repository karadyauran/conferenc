package com.karadyauran.conferenc.page;

import com.karadyauran.conferenc.dto.normal.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface UserPage
{
    @GetMapping("/{username}")
    ResponseEntity<UserDto> findByUsername(@PathVariable String username);
}
