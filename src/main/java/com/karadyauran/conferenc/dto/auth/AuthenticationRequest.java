package com.karadyauran.conferenc.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest
{
    private String username;

    private String password;

    @Override
    public String toString()
    {
        return username + " " + password;
    }
}
