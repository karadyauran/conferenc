package com.karadyauran.conferenc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "event_category_mapping")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCategoryMapping
{

    @EmbeddedId
    EventMappingId id;

    @Data
    @Embeddable
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class EventMappingId implements Serializable
    {
        @Column(name = "event_id")
        UUID eventId;

        @Column(name = "category_id")
        UUID categoryId;
    }
}
