package com.example.bhd.service.impl;

import com.example.bhd.dto.MovieDetailDTO;
import com.example.bhd.dto.MovieShowDTO;
import com.example.bhd.dto.ShowtimeDTO;
import com.example.bhd.dto.ViewMovieDTO;
import com.example.bhd.entity.Movie;
import com.example.bhd.entity.Showtime;
import com.example.bhd.repository.MovieRepository;
import com.example.bhd.repository.ShowtimeRepository;
import com.example.bhd.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final ShowtimeRepository showtimeRepository;

    private ObjectMapper objectMapper;

    @Override
    public List<ViewMovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(m -> new ViewMovieDTO(
                        m.getId(),
                        m.getImageUrl(),
                        m.getMovieName(),
                        m.getDuration(),
                        m.getRequiredAge(),
                        m.getIsAvailable()
                ))
                .toList();
    }

    @Override
    public MovieDetailDTO getMovieById(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        return MovieDetailDTO.builder()
                .actor(movie.getActor())
                .genre(movie.getGenre())
                .description(movie.getDescription())
                .director(movie.getDirector())
        .build();
    }

    @Override
    public List<MovieShowDTO> findByMovieAndShowtime(LocalDate showDate) {
        List<Movie> movies = movieRepository.findAllAvailableMovies();
        List<Showtime> showtimes = showtimeRepository.findByShowDate(showDate);
        Map<Integer, List<Showtime>> grouped = showtimes.stream()
                .collect(Collectors.groupingBy(st -> st.getMovie().getId()));

        // Build DTO list
        return movies.stream()
                .filter(m -> grouped.containsKey(m.getId())) // lọc phim không có suất chiếu trong ngày
                .map(m -> {
                    List<ShowtimeDTO> dtoList = grouped.get(m.getId()).stream()
                            .map(st -> new ShowtimeDTO(
                                    st.getShowDate(),
                                    st.getStartTime(),
                                    st.getRoom().getId(),
                                    st.getRoom().getRoomName()
                            ))
                            .toList();

                    return new MovieShowDTO(m.getId(), m.getMovieName(), dtoList);
                })
                .toList();
    }

}
