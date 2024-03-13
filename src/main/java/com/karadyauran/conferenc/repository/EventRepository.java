package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID>
{
    Optional<List<Event>> findAllByOrganizerId(UUID id);

    @Query("select e.capacity from Event e where e.id = :id")
    int getEventCapacityByEventId(UUID id);

    @Modifying
    @Query("update Event e set e.organizerId = :organizerId where e.id = :id")
    void changeOrganizer(UUID id, UUID organizerId);

    @Modifying
    @Query("update Event e set e.title = :title where e.id = :id")
    void changeTitle(UUID id, String title);

    @Modifying
    @Query("update Event e set e.description = :description where e.id = :id")
    void changeDescription(UUID id, String description);

    @Modifying
    @Query("update Event e set e.start = :start where e.id = :id")
    void changeStart(UUID id, Timestamp start);

    @Modifying
    @Query("update Event e set e.end = :end where e.id = :id")
    void changeEnd(UUID id, Timestamp end);

    @Modifying
    @Query("update Event e set e.location = :location where e.id = :id")
    void changeLocation(UUID id, String location);

    @Modifying
    @Query("update Event e set e.capacity = :capacity where e.id = :id")
    void changeCapacity(UUID id, int capacity);
}
