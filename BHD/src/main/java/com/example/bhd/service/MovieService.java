package com.example.bhd.service;

import com.example.bhd.dto.MovieDetailDTO;
import com.example.bhd.dto.ViewMovieDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<ViewMovieDTO> getAllMovies();
    MovieDetailDTO getMovieById(Integer movieId);

}
