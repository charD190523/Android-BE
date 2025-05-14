package com.example.bhd.restful.impl;

import com.example.bhd.dto.MovieDetailDTO;
import com.example.bhd.dto.MovieShowDTO;
import com.example.bhd.dto.ViewMovieDTO;
import com.example.bhd.factory.GeneralResponse;
import com.example.bhd.factory.ResponseFactory;
import com.example.bhd.repository.MovieRepository;
import com.example.bhd.restful.MovieOperations;
import com.example.bhd.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class MovieController implements MovieOperations {

    private final MovieService movieService;

    @Override
    public ResponseEntity<GeneralResponse<List<ViewMovieDTO>>> Viewmovie() {
        return ResponseEntity.ok(ResponseFactory.success(movieService.getAllMovies()));

    }

    @Override
    public ResponseEntity<GeneralResponse<MovieDetailDTO>> getMovieDetail(Integer movieId) {
        return ResponseEntity.ok(ResponseFactory.success(movieService.getMovieById(movieId)));
    }

    @Override
    public ResponseEntity<GeneralResponse<List<MovieShowDTO>>> findbyMovieAndShowtime(LocalDate showDate) {
        return ResponseEntity.ok(ResponseFactory.success(movieService.findByMovieAndShowtime(showDate)));
    }

    @Override
    public ResponseEntity<GeneralResponse<MovieShowDTO>> findByDate(Integer movieId, LocalDate showDate) {
        return ResponseEntity.ok(ResponseFactory.success(movieService.findByDate(movieId, showDate)));
    }
}
