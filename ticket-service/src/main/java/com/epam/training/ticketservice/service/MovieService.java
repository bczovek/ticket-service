package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.MovieDTO;
import com.epam.training.ticketservice.service.exception.OperationNotAllowedException;

import java.util.List;

public interface MovieService {

    void createMovie(String title, String genre, int length);

    void updateMovie(String title, String genre, int length);

    List<MovieDTO> listMovies();

    void deleteMovie(String title);
}
