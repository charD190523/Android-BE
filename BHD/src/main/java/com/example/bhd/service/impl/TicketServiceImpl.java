package com.example.bhd.service.impl;

import com.example.bhd.dto.SeatDetailListDTO;
import com.example.bhd.entity.SeatDetail;
import com.example.bhd.entity.Ticket;
import com.example.bhd.repository.SeatDetailRepository;
import com.example.bhd.security.CustomUserDetails;
import com.example.bhd.service.SeatDetailService;
import com.example.bhd.service.SessionService;
import com.example.bhd.service.TicketService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final SessionService sessionService;

    private final SeatDetailRepository seatDetailRepository;

    @Override
    public List<Ticket> createTicket(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer currentUserId = userDetails.getUser().getId();
        List<Ticket> ticketList = new ArrayList<>();
        SeatDetailListDTO seatDetailListDTO = (SeatDetailListDTO) sessionService.getAttribute(session,"seatDetailListDTO_" + currentUserId);
        for (SeatDetail seatDetail : seatDetailListDTO.getSeatDetailList()) {
            Ticket ticket = Ticket.builder()
                    .price(seatDetail.getSeat().getPrice())
                    .seatDetail(seatDetail)
                    .build();
            ticketList.add(ticket);
        }
//        sessionService.addAttribute(session, "ticketList", ticketList);
        return ticketList;
    }
}
