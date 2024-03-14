package com.karadyauran.conferenc.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateDto
{
    @Size(min = 3, max = 255)
    String username;
    @Email
    String email;
    String role;
    @Size(min = 8, max = 255)
    String password;
}
