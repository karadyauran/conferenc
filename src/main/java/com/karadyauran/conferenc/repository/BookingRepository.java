package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.Booking;
import com.karadyauran.conferenc.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID>
{
    Optional<List<Booking>> findAllByUserId(UUID id);

    @Query("select b.numberOfAttendees from Booking b where b.eventId = :eventId order by b.eventId desc limit 1")
    Optional<Integer> findLastBookingCapacityForEvent(UUID eventId);

    @Modifying
    @Query("update Booking b set b.status = :status where b.id = :id")
    void changeStatus(UUID id, Status status);
}
