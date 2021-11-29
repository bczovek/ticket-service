package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    Authenticator getAuthenticator();

    void createMovie(String title, String genre, int length);

    void updateMovie(String title, String genre, int length);

    void deleteMovie(String title);

    List<Movie> listMovies();


}
