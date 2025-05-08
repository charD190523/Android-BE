package com.example.bhd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewMovieDTO {
    private String imageUrl;
    private String movieName;
    private LocalTime duration;
    private Integer requiredAge;
}
