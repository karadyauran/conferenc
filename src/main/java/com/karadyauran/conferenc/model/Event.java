package com.karadyauran.conferenc.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
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
@Table(name = "events")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event
{
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;

    @Column(name = "organizer_id")
    UUID organizerId;

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
    Integer capacity;

    @Column(name = "is_public")
    Boolean isPublic;

    @OneToMany(mappedBy = "event")
    List<Booking> bookings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", insertable = false, updatable = false)
    User organizer;

    @OneToOne(mappedBy = "event")
    Session session;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "event_category_mapping",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private List<EventCategory> categories;

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