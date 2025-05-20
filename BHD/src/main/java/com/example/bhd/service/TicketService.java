package com.example.bhd.service;

import com.example.bhd.entity.Ticket;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface TicketService {

    List<Ticket> createTicket(HttpSession session);
}
