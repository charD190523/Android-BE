package com.example.bhd.service.impl;

import com.example.bhd.dto.MovieDetailDTO;
import com.example.bhd.dto.ViewMovieDTO;
import com.example.bhd.entity.Movie;
import com.example.bhd.repository.MovieRepository;
import com.example.bhd.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    private ObjectMapper objectMapper;

    @Override
    public List<ViewMovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> objectMapper.convertValue(movie, ViewMovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDetailDTO getMovieById(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        return objectMapper.convertValue(movie, MovieDetailDTO.class);
    }

}
