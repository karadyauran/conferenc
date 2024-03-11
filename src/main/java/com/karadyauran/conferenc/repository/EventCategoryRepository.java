package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, UUID>
{
    @Modifying
    @Query("update EventCategory e set e.name = :newName where e.id = :id")
    void changeName(UUID id, String newName);

    @Modifying
    @Query("update EventCategory e set e.description = :description where e.id = :id")
    void changeDescription(UUID id, String newDescription);
}
