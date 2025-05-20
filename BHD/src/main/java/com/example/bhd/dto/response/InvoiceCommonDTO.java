package com.example.bhd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCommonDTO {
    private Integer id;
    private String movieName;
    private LocalDate showDate;
    private LocalTime startTime;
    private Float totalPrice;
}
