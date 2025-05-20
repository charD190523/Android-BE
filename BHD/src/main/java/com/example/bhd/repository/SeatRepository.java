package com.example.bhd.repository;

import com.example.bhd.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Optional<Seat> findBySeatName(String seatName);
}
