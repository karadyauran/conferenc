package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID>
{

}
