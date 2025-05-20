package com.example.bhd.service;

import com.example.bhd.dto.SeatDetailDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface SeatDetailService {

    List<SeatDetailDTO> getAllSeatDetail(Integer showtimeId, HttpSession session);
    String holdSeat(Integer showtimeId, String seatName, HttpSession session);

}
