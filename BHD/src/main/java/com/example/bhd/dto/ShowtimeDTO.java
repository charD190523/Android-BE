package com.example.bhd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeDTO {
    private Integer id;
    private LocalDate showDate;
    private LocalTime startTime;
    private Integer roomId;
    private String roomName;
}
