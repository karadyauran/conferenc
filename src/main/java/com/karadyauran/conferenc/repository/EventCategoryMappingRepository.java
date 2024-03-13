package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.EventCategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventCategoryMappingRepository extends JpaRepository<EventCategoryMapping, EventCategoryMapping.EventMappingId>
{
    @Modifying
    @Query(value = "INSERT INTO event_category_mapping (event_id, category_id) VALUES (:eventId, :categoryId)", nativeQuery = true)
    void recordChanging(UUID eventId, UUID categoryId);
}

