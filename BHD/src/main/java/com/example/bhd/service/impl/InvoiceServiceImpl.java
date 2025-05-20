package com.example.bhd.service.impl;

import com.example.bhd.dto.FoodDetailDTO;
import com.example.bhd.dto.response.InvoiceResponse;
import com.example.bhd.entity.*;
import com.example.bhd.repository.*;
import com.example.bhd.security.CustomUserDetails;
import com.example.bhd.service.InvoiceService;
import com.example.bhd.service.SessionService;
import com.example.bhd.service.TicketService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final SessionService sessionService;

    private final TicketService ticketService;

    private final FoodRepository foodRepository;

    private final FoodDetailRepository foodDetailRepository;

    private final InvoiceRepository invoiceRepository;

    private final TicketRepository ticketRepository;

    private final EntityManager entityManager;
    private final SeatDetailRepository seatDetailRepository;

    @Override
    @Transactional
    public InvoiceResponse createInvoice(List<FoodDetailDTO> foodDetailList, HttpSession session) {
        Invoice invoice = new Invoice();
        List<Ticket> ticketList = ticketService.createTicket(session);
        invoice.setTickets(ticketList);

        // Ánh xạ từ List<FoodDetailDTO> sang List<FoodDetail>
        if (foodDetailList != null && !foodDetailList.isEmpty()) {
            List<FoodDetail> foodDetails = new ArrayList<>();
            for (FoodDetailDTO dto : foodDetailList) {
                FoodDetail foodDetail = new FoodDetail();
                foodDetail.setQuantity(dto.getQuantity());

                Food food = foodRepository.findById(dto.getFoodId())
                        .orElseThrow(() -> new RuntimeException("Food not found with id: " + dto.getFoodId()));
                foodDetail.setFood(food);

                foodDetail.setInvoice(invoice);
                foodDetails.add(foodDetail);
            }
            // Set food details to invoice
            invoice.setFoodDetailList(foodDetails);
        }
        if (ticketList != null && !ticketList.isEmpty()) {
            invoice.setTicketList(ticketList);
        }

        // Tính tổng tiền
        invoice.calculateTotalAmount();
        sessionService.addAttribute(session, "invoice", invoice);
        return InvoiceResponse.builder()
                .countTicket(invoice.getTickets() != null ? invoice.getTickets().size() : 0)
                .seatName(invoice.getTickets() != null ? invoice.getTickets().stream()
                        .map(ticket -> ticket.getSeatDetail().getSeat().getSeatName())
                        .collect(Collectors.toList()) : new ArrayList<>())
                .ticketPrice(invoice.getTickets() != null ? invoice.getTickets().stream()
                        .map(Ticket::getPrice)
                        .reduce(0.0f, Float::sum) : 0.0f)
                .foodPrice(invoice.getFoodDetails() != null ? invoice.getFoodDetails().stream()
                        .map(fd -> fd.getFood().getPrice() * fd.getQuantity())
                        .reduce(0.0f, Float::sum) : 0.0f)
                .totalPrice(invoice.getTotalPrice() != null ? invoice.getTotalPrice() : 0.0f)
                .build();
    }

    @Override
    @Transactional
    public void saveInvoice(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User currentUser = userDetails.getUser();

        Invoice invoice = (Invoice) sessionService.getAttribute(session, "invoice");
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setUser(currentUser);
        invoiceRepository.save(invoice);
        if (invoice.getFoodDetails() != null) {
            invoice.getFoodDetails().forEach(foodDetail -> foodDetail.setInvoice(invoice));
        }
        ticketRepository.saveAll(invoice.getTickets());
        List<SeatDetail> seatDetailsToUpdate = invoice.getTickets().stream()
                .map(Ticket::getSeatDetail)
                .peek(seatDetail -> seatDetail.setStatus("BOOKED"))
                .toList();
        seatDetailRepository.saveAll(seatDetailsToUpdate);
        sessionService.removeAllAttributes(session);
    }
}
