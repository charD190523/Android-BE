package com.example.bhd.repository;

import com.example.bhd.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Optional<Seat> findBySeatName(String seatName);

    @Query(value = "SELECT s.* FROM seat s " +
            "LEFT JOIN seat_detail sd ON s.id = sd.seat_id " +
            "LEFT JOIN ticket t ON t.seat_detail_id = sd.id " +
            "LEFT JOIN invoice i ON i.id = t.invoice_id " +
            "WHERE i.id = :id", nativeQuery = true)
    Optional<List<Seat>> findByInvoiceId(@Param("id") int id);
}
