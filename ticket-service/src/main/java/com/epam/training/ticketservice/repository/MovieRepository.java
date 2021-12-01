package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.repository.entity.Movie;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface MovieRepository extends Repository<Movie, Long> {
    void save(Movie movie);

    Optional<Movie> findMovieByTitle(String name);

    Stream<Movie> findAll();

    void deleteByTitle(String title);
}
