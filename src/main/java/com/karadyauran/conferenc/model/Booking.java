package com.karadyauran.conferenc.model;

import com.karadyauran.conferenc.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking
{
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "event_id")
    UUID eventId;

    @Column(name = "status")
    Status status;

    @Column(name = "number_of_attendees")
    int numberOfAttendees;

    @ManyToOne
    User users;

    @ManyToOne
    Event event;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(userId, booking.userId) && Objects.equals(eventId, booking.eventId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, userId, eventId);
    }

    @Override
    public String toString()
    {
        return id + " - " + status;
    }
}
