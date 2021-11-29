package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.TicketService;

import java.util.List;

public class BasicTicketService implements TicketService {

    private final Authenticator authenticator;
    private final MovieRepository movieRepository;

    public BasicTicketService(Authenticator authenticator, MovieRepository movieRepository) {
        this.authenticator = authenticator;
        this.movieRepository = movieRepository;
    }

    @Override
    public Authenticator getAuthenticator() {
        return authenticator;
    }

    @Override
    public void createMovie(String title, String genre, int length) {

    }

    @Override
    public void updateMovie(String title, String genre, int length) {

    }

    @Override
    public void deleteMovie(String title) {

    }

    @Override
    public List<Movie> listMovies() {
        return null;
    }
}
