package com.example.bhd.restful.impl;

import com.example.bhd.entity.Movie;
import com.example.bhd.factory.GeneralResponse;
import com.example.bhd.factory.ResponseFactory;
import com.example.bhd.repository.MovieRepository;
import com.example.bhd.restful.UploadOperations;
import com.example.bhd.service.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;

@RestController
@AllArgsConstructor
public class UploadController implements UploadOperations {

    private final MovieRepository movieRepository;

    private final UploadService uploadService;

    @Override
    public ResponseEntity<GeneralResponse<Movie>> uploadMovie(@RequestPart("file") MultipartFile file,
                                                       @RequestParam("movieName") String movieName,
                                                       @RequestParam("duration") LocalTime duration, // format: "HH:mm:ss"
                                                       @RequestParam("genre") String genre,
                                                       @RequestParam("director") String director,
                                                       @RequestParam("actor") String actor,
                                                       @RequestParam("requiredAge") Integer requiredAge,
                                                       @RequestParam("isAvailable") Boolean isAvailable) {
        try {
            Movie movie = Movie.builder()
                    .movieName(movieName)
                    .duration(duration)
                    .genre(genre)
                    .director(director)
                    .actor(actor)
                    .requiredAge(requiredAge)
                    .isAvailable(isAvailable)
                    .build();
            return ResponseEntity.ok(ResponseFactory.success(uploadService.uploadMovie(file, movie)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseFactory.fail("Failed to upload movie: " + e.getMessage()));
        }
    }
}
