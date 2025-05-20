package com.example.bhd.repository;

import com.example.bhd.dto.ShowtimeDTO;
import com.example.bhd.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
    
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    Optional<Showtime> findById(Integer id);

    List<Showtime> findByShowDate(LocalDate showDate);

    @Query("""
    SELECT new com.example.bhd.dto.ShowtimeDTO(
        s.id,
        s.showDate,
        s.startTime,
        s.room.id,
        s.room.roomName
    )
    FROM Showtime s
    WHERE s.movie.id = :movieId AND s.showDate = :showDate""")
    List<ShowtimeDTO> findByMovieAndShowDate(@Param("movieId") Integer movieId, @Param("showDate") LocalDate showDate);

}
