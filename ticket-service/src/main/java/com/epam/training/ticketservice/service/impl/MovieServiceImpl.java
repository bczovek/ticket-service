package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.MovieDto;
import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.entity.Movie;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.MovieService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final Authenticator authenticator;

    public MovieServiceImpl(final MovieRepository movieRepository, final Authenticator authenticator) {
        this.movieRepository = movieRepository;
        this.authenticator = authenticator;
    }

    @Override
    public void createMovie(String title, String genre, int length) {
        authenticator.verify(List.of(AccountLevel.ADMINISTRATOR));
        if (movieRepository.findMovieByTitle(title).isEmpty()) {
            Movie movie = new Movie(title, genre, length);
            movieRepository.save(movie);
        }
    }

    @Override
    public void updateMovie(String title, String genre, int length) {
        authenticator.verify(List.of(AccountLevel.ADMINISTRATOR));
        if (movieRepository.findMovieByTitle(title).isPresent()) {
            Movie movie = new Movie(title, genre, length);
            movieRepository.save(movie);
        }
    }

    @Override
    public List<MovieDto> listMovies() {
        return movieRepository.findAll()
                .map(movie -> new MovieDto(movie.getTitle(), movie.getGenre(), movie.getLength()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteMovie(String title) {
        authenticator.verify(List.of(AccountLevel.ADMINISTRATOR));
        if (movieRepository.findMovieByTitle(title).isPresent()) {
            movieRepository.deleteByTitle(title);
        }
    }
}
