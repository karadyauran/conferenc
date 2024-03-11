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
@Table(name = "user_sessions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSession
{

    @EmbeddedId
    UserSessionId id;

    String status;

    @Data
    @Embeddable
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UserSessionId implements Serializable
    {
        @Column(name = "user_id")
        UUID userId;

        @Column(name = "session_id")
        UUID sessionId;
    }
}
