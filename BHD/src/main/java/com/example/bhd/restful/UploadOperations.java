package com.example.bhd.restful;

import com.example.bhd.entity.Movie;
import com.example.bhd.factory.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;

@RequestMapping("/api/admin/upload/")
public interface UploadOperations {

    @PostMapping("/movie")
    ResponseEntity<GeneralResponse<Movie>> uploadMovie (MultipartFile file,
                                                        String movieName,
                                                        LocalTime duration,
                                                        String genre,
                                                        String director,
                                                        String actor,
                                                        Integer requiredAge,
                                                        Boolean isAvailable,
                                                        String description); // format: "HH:mm:ss"
}
