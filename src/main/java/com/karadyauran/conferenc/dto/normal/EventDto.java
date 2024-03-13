package com.karadyauran.conferenc.dto.normal;

import com.karadyauran.conferenc.dto.shorted.EventCategoryShortDto;
import com.karadyauran.conferenc.dto.shorted.UserShortDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventDto
{
    String id;
    UserShortDto organizer;
    String title;
    String description;
    Timestamp start;
    Timestamp end;
    String location;
    Integer capacity;
    Boolean isPublic;
    SessionDto session;
    List<BookingDto> bookings;
    List<EventCategoryShortDto> categories;
}
