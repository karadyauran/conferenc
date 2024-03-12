package com.karadyauran.conferenc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Builder
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

    @Column(name = "title")
    String title;

    @Column(name = "start_time")
    Timestamp start;

    @Column(name = "end_time")
    Timestamp end;

    @Column(name = "speaker")
    String speaker;

    @Column(name = "location")
    String location;

    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", insertable = false, updatable = false)
    Event event;

    @ManyToMany(mappedBy = "sessions")
    private List<User> users;

    public Session(UUID id, UUID eventId, String title, Timestamp start, Timestamp end, String speaker, String location, Event event, List<User> users)
    {
        this.id = id;
        this.eventId = eventId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.speaker = speaker;
        this.location = location;
        this.event = event;
        this.users = users;
    }

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
