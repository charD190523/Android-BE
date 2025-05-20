package com.example.bhd.service;

import com.example.bhd.dto.MovieDetailDTO;
import com.example.bhd.dto.MovieShowDTO;
import com.example.bhd.dto.ViewMovieDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MovieService {
    List<ViewMovieDTO> getAllMovies();
    MovieDetailDTO getMovieById(Integer movieId);
    List<MovieShowDTO>  findByMovieAndShowtime(LocalDate showDate);
    MovieShowDTO findByDate(Integer movieId, LocalDate showDate); // Chuyển đổi từ String sang LocalTime
}
