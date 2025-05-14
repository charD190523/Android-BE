package com.example.bhd.repository;

import com.example.bhd.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findById(Integer id);
    List<Movie> findAll();
    @Query("SELECT m FROM Movie m WHERE m.isAvailable = true")
    List<Movie> findAllAvailableMovies();

    // Custom query methods can be defined here if needed
}
