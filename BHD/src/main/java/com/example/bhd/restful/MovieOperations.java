package com.example.bhd.restful;

import com.example.bhd.dto.MovieDetailDTO;
import com.example.bhd.dto.ViewMovieDTO;
import com.example.bhd.factory.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api/movie")
public interface MovieOperations {

    @GetMapping("/getAll")
    ResponseEntity<GeneralResponse<List<ViewMovieDTO>>> Viewmovie();
    @GetMapping("get-detail")
    ResponseEntity<GeneralResponse<MovieDetailDTO>> getMovieDetail(@RequestParam("movieId") Integer movieId);
}
