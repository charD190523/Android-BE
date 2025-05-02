package com.example.bhd.service;

import com.example.bhd.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    Movie uploadMovie(MultipartFile file, Movie movie);
}
