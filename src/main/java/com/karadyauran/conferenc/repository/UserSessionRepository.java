package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, UserSession.UserSessionId>
{
    @Modifying
    @Query(value = "INSERT INTO user_sessions (user_id, session_id, status) VALUES (:userId, :sessionId, :status)", nativeQuery = true)
    void recordChanging(UUID userId, UUID sessionId, String status);
}

