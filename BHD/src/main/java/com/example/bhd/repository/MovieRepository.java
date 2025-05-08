package com.example.bhd.repository;

import com.example.bhd.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findById(Integer id);
    List<Movie> findAll();
    // Custom query methods can be defined here if needed
}
