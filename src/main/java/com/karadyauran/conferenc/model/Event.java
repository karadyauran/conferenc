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
@Table(name = "events")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event
{
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "start_time")
    Timestamp start;

    @Column(name = "end_time")
    Timestamp end;

    @Column(name = "location")
    String location;

    @Column(name = "capacity")
    int capacity;

    @Column(name = "is_public")
    boolean isPublic;

    @OneToOne(mappedBy = "event")
    Session session;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public String toString()
    {
        return title + ": " + description;
    }
}
