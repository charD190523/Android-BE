package com.example.bhd.service.impl;

import com.example.bhd.dto.SeatDetailDTO;
import com.example.bhd.dto.SeatDetailListDTO;
import com.example.bhd.entity.Seat;
import com.example.bhd.entity.SeatDetail;
import com.example.bhd.entity.Showtime;
import com.example.bhd.repository.SeatDetailRepository;
import com.example.bhd.repository.SeatRepository;
import com.example.bhd.repository.ShowtimeRepository;
import com.example.bhd.security.CustomUserDetails;
import com.example.bhd.service.SeatDetailService;
import com.example.bhd.service.SessionService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SeatDetailServiceImpl implements SeatDetailService {

    private final SeatDetailRepository seatDetailRepository;

    private final SeatRepository seatRepository;

    private final SessionService sessionService;
    private final ShowtimeRepository showtimeRepository;

    @Override
    public List<SeatDetailDTO> getAllSeatDetail(Integer showtimeId, HttpSession session) {
        List<SeatDetailDTO> seatDetailDTOList = seatDetailRepository.findAllByShowtimeId(showtimeId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer currentUserId = userDetails.getUser().getId();

        // Khởi tạo hoặc lấy seatDetailListDTO từ session cho user hiện tại
        SeatDetailListDTO seatDetailListDTO = (SeatDetailListDTO) sessionService.getAttribute(session, "seatDetailListDTO_" + currentUserId);
        if (seatDetailListDTO == null) {
            seatDetailListDTO = new SeatDetailListDTO();
            sessionService.addAttribute(session, "seatDetailListDTO_" + currentUserId, seatDetailListDTO);
        }

        if (seatDetailDTOList != null) {
            for (SeatDetailDTO seatDetailDTO : seatDetailDTOList) {
                log.info("Processing seatDetailDTO: id={}, status={}, userId={}",
                        seatDetailDTO.getId(), seatDetailDTO.getStatus(), seatDetailDTO.getUserId());

                Integer seatUserId = seatDetailDTO.getUserId();

                if ("HOLD".equals(seatDetailDTO.getStatus())) {
                    if (seatUserId != null) {
                        if (seatUserId.equals(currentUserId)) {
                            seatDetailDTO.setStatus("HOLD_BY_CURRENT_USER");
                        } else {
                            seatDetailDTO.setStatus("HOLD_BY_ANOTHER_USER");
                        }
                    } else {
                        seatDetailDTO.setStatus("AVAILABLE");
                    }
                }
            }
            // Cập nhật session với danh sách mới
            sessionService.addAttribute(session, "seatDetailListDTO_" + currentUserId , seatDetailListDTO);
        }

        return seatDetailDTOList;
    }

    @Override
    @Transactional
    public String holdSeat(Integer showtimeId, String seatName, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer currentUserId = userDetails.getUser().getId();

        // Sử dụng key session duy nhất cho mỗi user
        SeatDetailListDTO seatDetailListDTO = (SeatDetailListDTO) sessionService.getAttribute(session, "seatDetailListDTO_"+ currentUserId);

        if (seatDetailListDTO == null) {
            seatDetailListDTO = new SeatDetailListDTO();
        }

        Seat seat = seatRepository.findBySeatName(seatName)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        // Tìm xem ghế đã bị giữ chưa
        SeatDetail existingSeatDetail = seatDetailRepository
                .findByShowtimeIdAndSeatId(showtimeId, seat.getId())
                .orElse(null);

        if (existingSeatDetail != null) {
            // Kiểm tra trạng thái của ghế
            if ("BOOKED".equals(existingSeatDetail.getStatus())) {
                return "Seat is already booked and cannot be removed";
            } else if ("HOLD".equals(existingSeatDetail.getStatus())) {
                if (existingSeatDetail.getUserId().equals(currentUserId)) {
                    // Hủy giữ ghế
                    seatDetailRepository.removeSeatDetail(existingSeatDetail.getId(), currentUserId);
                    seatDetailListDTO.removeSeatDetail(existingSeatDetail);
                    sessionService.addAttribute(session, "seatDetailListDTO_" + currentUserId, seatDetailListDTO);
                    return "Removed hold seat successfully";
                } else {
                    return "Seat is currently held by another user";
                }
            }
        }

        // Tạo mới SeatDetail để giữ ghế
        SeatDetail newSeatDetail = SeatDetail.builder()
                .showtime(showtime)
                .seat(seat)
                .userId(currentUserId)
                .status("HOLD")
                .build();
        seatDetailRepository.save(newSeatDetail);
        seatDetailListDTO.addSeatDetail(newSeatDetail);
        sessionService.addAttribute(session, "seatDetailListDTO_" + currentUserId, seatDetailListDTO);

        return "Hold seat successfully";
    }

}
