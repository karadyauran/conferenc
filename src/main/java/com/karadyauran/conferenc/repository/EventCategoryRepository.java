package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, UUID>
{

}
