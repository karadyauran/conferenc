package com.karadyauran.conferenc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "sessions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Session
{
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;

    @Column(name = "event_id")
    UUID eventId;

    @Column(name = "start_time")
    Timestamp start;

    @Column(name = "end_time")
    Timestamp end;

    @Column(name = "speaker")
    String speaker;

    @Column(name = "location")
    String location;

    @OneToOne(mappedBy = "session")
    Event event;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(eventId, session.eventId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, eventId);
    }

    @Override
    public String toString()
    {
        return eventId + " " + speaker;
    }
}
