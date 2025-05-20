package com.example.bhd.restful;

import com.example.bhd.dto.SeatDetailDTO;
import com.example.bhd.factory.GeneralResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api/seat-detail")
public interface SeatDetailOperation {

    @GetMapping("/get-all-seat")
    ResponseEntity<GeneralResponse<List<SeatDetailDTO>>> getAllSeatDetail(@RequestParam("showtimeId") Integer showtimeId, HttpSession session);

    @GetMapping("/hold")
    ResponseEntity<GeneralResponse<String>> holdSeat(@RequestParam("showtimeId") Integer seatDetailId, @RequestParam("seatName") String seatName, HttpSession session);
}
