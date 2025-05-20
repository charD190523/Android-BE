package com.example.bhd.dto.response;

import com.example.bhd.dto.SeatDetailDTO;
import com.example.bhd.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailDTO {

    private String movieName;
    private LocalDate showDate;
    private LocalTime startTime;
    private List<Seat> seats;
    private Float totalPrice;
    private Double ticketPrice;
    private Float foodPrice;

    public InvoiceDetailDTO(String movieName, LocalDate showDate, LocalTime startTime, Float totalPrice, Double ticketPrice) {
        this.movieName = movieName;
        this.showDate = showDate;
        this.startTime = startTime;
        this.totalPrice = totalPrice;
        this.ticketPrice = ticketPrice;
    }
}
