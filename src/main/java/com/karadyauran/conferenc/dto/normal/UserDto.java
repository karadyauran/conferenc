package com.karadyauran.conferenc.dto.normal;

import com.karadyauran.conferenc.dto.shorted.EventShortDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto
{
    String username;
    String email;
    List<EventShortDto> events;
    List<BookingDto> bookings;
}
