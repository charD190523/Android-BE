package com.example.bhd.repository;

import com.example.bhd.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // Custom query methods can be defined here if needed
}
