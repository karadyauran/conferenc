package com.karadyauran.conferenc.dto.shorted;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventShortDto
{
    String title;
    String description;
    Timestamp start;
    Timestamp end;
    String location;
    Integer capacity;
    Boolean isPublic;
}
