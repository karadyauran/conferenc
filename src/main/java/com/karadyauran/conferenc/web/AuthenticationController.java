package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.dto.auth.AuthenticationRequest;
import com.karadyauran.conferenc.dto.auth.AuthenticationResponse;
import com.karadyauran.conferenc.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController
{

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    )
    {
        return ResponseEntity.ok(service.authenticate(request));
    }
}