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
public class EventCreateDto
{
    UUID organizerId;
    String title;
    String description;
    Timestamp start;
    Timestamp end;
    String location;
    Integer capacity;
    Boolean isPublic;
}
