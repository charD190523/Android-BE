package com.example.bhd.restful.impl;

import com.example.bhd.dto.SeatDetailDTO;
import com.example.bhd.factory.GeneralResponse;
import com.example.bhd.factory.ResponseFactory;
import com.example.bhd.restful.SeatDetailOperation;
import com.example.bhd.service.SeatDetailService;
import com.example.bhd.service.SessionService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SeatDetailController implements SeatDetailOperation {

    private final SessionService sessionService;

    private final SeatDetailService seatDetailService;

    @Override
    public ResponseEntity<GeneralResponse<List<SeatDetailDTO>>> getAllSeatDetail(Integer showtimeId, HttpSession session) {
        sessionService.startSession(session, showtimeId);
        return ResponseEntity.ok(ResponseFactory.success(seatDetailService.getAllSeatDetail(showtimeId,session )));
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> holdSeat(Integer showtimeId, String seatName, HttpSession session) {
        return ResponseEntity.ok(ResponseFactory.success(seatDetailService.holdSeat(showtimeId, seatName, session)));
    }
}
