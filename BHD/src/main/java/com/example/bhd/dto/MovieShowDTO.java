package com.example.bhd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieShowDTO {
    private Integer id;
    private String movieName;
    private List<ShowtimeDTO> showtimes;
}
