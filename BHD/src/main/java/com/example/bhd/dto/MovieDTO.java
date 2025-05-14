package com.example.bhd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private String movieName;
    private String genre;
    private String description;
    private String director;
    private String actor;
    private LocalTime duration;
    private Integer requiredAge;
    private String imageUrl;
    private Boolean isAvailable;
}
