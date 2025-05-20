package com.example.bhd.repository;

import com.example.bhd.dto.SeatDetailDTO;
import com.example.bhd.entity.SeatDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SeatDetailRepository extends JpaRepository<SeatDetail, Integer> {

    Optional<SeatDetail> findById(Integer id);

    Optional<SeatDetail> findByShowtimeIdAndSeatId(Integer showtimeId, Integer seatId);

    Optional<SeatDetail> findByShowtimeIdAndSeatIdAndUserId(Integer showtimeId, Integer seatId, Integer userId);

    @Query("SELECT new com.example.bhd.dto.SeatDetailDTO(s.id, s.status, s.seat.id, s.seat.seatName, s.userId) " +
           "FROM SeatDetail s WHERE s.showtime.id = :showtimeId AND (s.status = 'HOLD' OR s.status = 'BOOKED')")
    List<SeatDetailDTO> findAllByShowtimeId(@Param("showtimeId") Integer showtimeId);


//    @Modifying
//    @Transactional
//    @Query("UPDATE SeatDetail s SET s.status = :status WHERE s.id = :seatDetailId")
//    void updateSeatStatus(@Param("seatDetailId") Integer seatDetailId, @Param("status") String status);

    @Modifying
    @Transactional
    @Query ("DELETE FROM SeatDetail s WHERE s.id = :seatDetailId AND s.userId = :userId")
    void removeSeatDetail(@Param("seatDetailId") Integer seatDetailId, @Param("userId") Integer userId);
}
