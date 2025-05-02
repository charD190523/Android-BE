package com.example.bhd.service;

import com.example.bhd.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    Movie uploadMovie(MultipartFile file, Movie movie) throws IOException;
}
