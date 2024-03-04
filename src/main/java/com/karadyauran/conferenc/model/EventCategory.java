package com.karadyauran.conferenc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "sessions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCategory
{
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventCategory that = (EventCategory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
