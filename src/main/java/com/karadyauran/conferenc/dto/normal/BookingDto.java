package com.karadyauran.conferenc.dto.normal;

import com.karadyauran.conferenc.dto.shorted.UserShortDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDto
{
    UserShortDto users;
    String status;
    Integer numberOfAttendees;
}
