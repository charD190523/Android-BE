package com.example.bhd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse {
    private Integer countTicket;
    private List<String> seatName;
    private Float ticketPrice;
    private Float foodPrice;
    private Float totalPrice;
}
