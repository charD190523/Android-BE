package com.example.bhd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDetailDTO {
    private Integer id;
    private String status;
    private Integer seatId;
    private String seatName;
    private Integer userId;
}
