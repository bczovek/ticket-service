package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.entity.Movie;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class MovieServiceTest {

    private MovieService underTest;

    private static final String DUMMY_MOVIE_TITLE = "Title";
    private static final String DUMMY_MOVIE_GENRE = "Genre";
    private static final int DUMMY_MOVIE_LENGTH = 10;
    public static final List<AccountLevel> ALLOWED_ACCOUNT_LEVELS = List.of(AccountLevel.ADMINISTRATOR);

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Authenticator authenticator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieServiceImpl(movieRepository, authenticator);
    }

    @Test
    public void testCreateMovieShouldSaveMovieWhenGivenNewMovie() {
        // Given in set up

        // When
        underTest.createMovie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH);

        // Then
        Mockito.verify(authenticator).verify(ALLOWED_ACCOUNT_LEVELS);
        Mockito.verify(movieRepository).findMovieByTitle(DUMMY_MOVIE_TITLE);
        Mockito.verify(movieRepository).save(Mockito.any(Movie.class));
        Mockito.verifyNoMoreInteractions(movieRepository);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testCreateMovieShouldNotSaveMovieWhenGivenExistingMovie() {
        // Given
        Movie expected = new Movie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH);
        BDDMockito.given(movieRepository.findMovieByTitle(DUMMY_MOVIE_TITLE)).willReturn(Optional.of(expected));

        // When
        underTest.createMovie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH);

        // Then
        Mockito.verify(authenticator).verify(ALLOWED_ACCOUNT_LEVELS);
        Mockito.verify(movieRepository).findMovieByTitle(DUMMY_MOVIE_TITLE);
        Mockito.verifyNoMoreInteractions(movieRepository);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testUpdateMovieShouldUpdateMovieWhenGivenExistingMovie() {
        // Given
        Movie expected = new Movie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH);
        BDDMockito.given(movieRepository.findMovieByTitle(DUMMY_MOVIE_TITLE)).willReturn(Optional.of(expected));

        // When
        underTest.updateMovie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH);

        // Then
        Mockito.verify(authenticator).verify(ALLOWED_ACCOUNT_LEVELS);
        Mockito.verify(movieRepository).findMovieByTitle(DUMMY_MOVIE_TITLE);
        Mockito.verify(movieRepository).save(Mockito.any(Movie.class));
        Mockito.verifyNoMoreInteractions(movieRepository);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testUpdateMovieShouldNotUpdateMovieWhenGivenNewMovie() {
        // Given in set up

        // When
        underTest.updateMovie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH);

        // Then
        Mockito.verify(authenticator).verify(ALLOWED_ACCOUNT_LEVELS);
        Mockito.verify(movieRepository).findMovieByTitle(DUMMY_MOVIE_TITLE);
        Mockito.verifyNoMoreInteractions(movieRepository);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testListMoviesShouldListMovies() {
        // Given in set up
        // When
        underTest.listMovies();

        // Then
        Mockito.verify(movieRepository).findAll();
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testDeleteMovieShouldDeleteMovieWhenGivenExistingMovie() {
        // Given
        Movie expected = new Movie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH);
        BDDMockito.given(movieRepository.findMovieByTitle(DUMMY_MOVIE_TITLE)).willReturn(Optional.of(expected));

        // When
        underTest.deleteMovie(DUMMY_MOVIE_TITLE);

        // Then
        Mockito.verify(authenticator).verify(ALLOWED_ACCOUNT_LEVELS);
        Mockito.verify(movieRepository).findMovieByTitle(DUMMY_MOVIE_TITLE);
        Mockito.verify(movieRepository).deleteByTitle(DUMMY_MOVIE_TITLE);
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testDeleteMovieShouldNotDeleteMovieWhenGivenNewMovie() {
        // Given in set up

        // When
        underTest.deleteMovie(DUMMY_MOVIE_TITLE);

        // Then
        Mockito.verify(authenticator).verify(ALLOWED_ACCOUNT_LEVELS);
        Mockito.verify(movieRepository).findMovieByTitle(DUMMY_MOVIE_TITLE);
        Mockito.verifyNoMoreInteractions(movieRepository);
    }
}
