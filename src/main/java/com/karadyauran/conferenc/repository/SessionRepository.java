package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID>
{
    @Modifying
    @Query("update Session s set s.start = :start where s.id = :id")
    void changeStart(UUID id, Timestamp start);

    @Modifying
    @Query("update Session s set s.end = :end where s.id = :id")
    void changeEnd(UUID id, Timestamp end);

    @Modifying
    @Query("update Session s set s.speaker = :speaker where s.id = :id")
    void changeSpeaker(UUID id, String speaker);

    @Modifying
    @Query("update Session s set s.location = :location where s.id = :id")
    void changeLocation(UUID id, String location);
}
