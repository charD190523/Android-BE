package com.example.bhd.service.impl;

import com.example.bhd.dto.SeatDetailListDTO;
import com.example.bhd.entity.SeatDetail;
import com.example.bhd.repository.SeatDetailRepository;
import com.example.bhd.security.CustomUserDetails;
import com.example.bhd.service.SessionService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SeatDetailRepository seatDetailRepository;

    public void startSession(HttpSession session, Integer showtimeId) {
        session.setAttribute("showtimeId", showtimeId);
        session.setMaxInactiveInterval(420); // 7 phút
    }

    public void addAttribute(HttpSession session, String key, Object value) {
        session.setAttribute(key, value);
    }

    public Object getAttribute(HttpSession session, String key) {
        return session.getAttribute(key);
    }

    @Override
    public void removeAttribute(HttpSession session, String key) {
        session.removeAttribute(key);
    }

    public void clearSession(HttpSession session) {
        session.invalidate();
    }


    @Override
    public void removeAllAttributes(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer currentUserId = userDetails.getUser().getId();

        SeatDetailListDTO seatDetailListDTO = (SeatDetailListDTO) session.getAttribute("seatDetailListDTO_" + currentUserId);

        if (seatDetailListDTO != null && seatDetailListDTO.getSeatDetailList() != null) {
            for (SeatDetail seatDetail : seatDetailListDTO.getSeatDetailList()) {
                // Chỉ xóa nếu ghế đang được giữ (HOLD)
                if ("HOLD_BY_CURRENT_USER".equals(seatDetail.getStatus()) || "HOLD_BY_ANOTHER_USER".equals(seatDetail.getStatus())) {
                    seatDetailRepository.removeSeatDetail(seatDetail.getId(), currentUserId);
                }
            }
        }

        // Chỉ xóa các attributes liên quan đến user hiện tại (nên dùng prefix hoặc xóa có điều kiện)
        var attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            if (attributeName.contains(String.valueOf(currentUserId))) {
                session.removeAttribute(attributeName);
            }
        }
    }

}
