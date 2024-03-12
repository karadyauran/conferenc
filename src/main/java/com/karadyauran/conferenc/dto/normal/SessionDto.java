package com.karadyauran.conferenc.dto.normal;

import com.karadyauran.conferenc.dto.shorted.EventShortDto;
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
public class SessionDto
{
    String id;
    EventShortDto event;
    Timestamp start;
    Timestamp end;
    String speaker;
    String location;
}
