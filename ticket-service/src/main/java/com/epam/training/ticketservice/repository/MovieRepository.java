package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.model.Movie;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MovieRepository extends Repository<Movie, Long> {
    void save(Movie movie);

    Movie findMovieByName(Long id);

    List<Movie> findAll();

    void deleteByName();
}
