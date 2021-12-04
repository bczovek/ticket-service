package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.MovieDto;

import java.util.List;

public interface MovieService {

    void createMovie(String title, String genre, int length);

    void updateMovie(String title, String genre, int length);

    List<MovieDto> listMovies();

    void deleteMovie(String title);
}
