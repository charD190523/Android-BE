package com.example.bhd.repository;

import com.example.bhd.dto.ShowtimeDTO;
import com.example.bhd.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
    List<Showtime> findByShowDate(LocalDate showDate);

}
