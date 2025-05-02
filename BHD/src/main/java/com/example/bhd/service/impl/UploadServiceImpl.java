package com.example.bhd.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.bhd.entity.Movie;
import com.example.bhd.repository.MovieRepository;
import com.example.bhd.service.UploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final Cloudinary cloudinary;

    private final ObjectMapper objectMapper;

    private final MovieRepository movieRepository;

    @Override
    public Movie uploadMovie(MultipartFile file, Movie movie) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("secure_url");
        movie.setImageUrl(imageUrl);
        movieRepository.save(movie);
        return movie;
    }
}
