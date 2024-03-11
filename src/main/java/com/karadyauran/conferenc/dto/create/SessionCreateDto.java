package com.karadyauran.conferenc.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionCreateDto
{
    UUID userId;
    UUID eventId;
    Timestamp start;
    Timestamp end;
    String speaker;
    String status;
}
