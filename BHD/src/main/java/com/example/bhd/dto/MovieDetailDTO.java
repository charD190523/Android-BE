package com.example.bhd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailDTO {
    private String genre;
    private String description;
    private String director;
    private String actor;
}
