package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import com.epam.training.ticketservice.repository.entity.Movie;
import com.epam.training.ticketservice.repository.entity.Room;
import com.epam.training.ticketservice.repository.entity.Screening;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.exception.NoSuchMovieException;
import com.epam.training.ticketservice.service.exception.NoSuchRoomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ScreeningServiceTest {

    private ScreeningService underTest;
    private static final String DUMMY_MOVIE_TITLE = "Title";
    private static final String DUMMY_MOVIE_GENRE = "Genre";
    private static final int DUMMY_MOVIE_LENGTH = 30;
    private static final String DUMMY_ROOM_NAME = "Name";
    private static final int DUMMY_ROOM_ROWS = 5;
    private static final int DUMMY_ROOM_COLUMNS = 5;
    private static final String DUMMY_START_DATE = "2021-01-01 12:00";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ScreeningRepository screeningRepository;

    @Mock
    private Authenticator authenticator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ScreeningServiceImpl(screeningRepository, movieRepository, roomRepository, authenticator);
    }

    @Test
    public void testCreateScreeningShouldSaveScreeningWhenNoOverlappingScreenings() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);
        BDDMockito.given(movieRepository.findMovieByTitle(DUMMY_MOVIE_TITLE))
                .willReturn(Optional.of(new Movie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH)));
        BDDMockito.given(roomRepository.findRoomByName(DUMMY_ROOM_NAME))
                .willReturn(Optional.of(new Room(DUMMY_ROOM_NAME, DUMMY_ROOM_ROWS, DUMMY_ROOM_COLUMNS)));

        // When
        underTest.createScreening(DUMMY_MOVIE_TITLE, DUMMY_ROOM_NAME, DUMMY_START_DATE);

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(movieRepository).findMovieByTitle(DUMMY_MOVIE_TITLE);
        Mockito.verify(roomRepository).findRoomByName(DUMMY_ROOM_NAME);
        Mockito.verify(screeningRepository).findScreeningsByRoom(Mockito.any(Room.class));
        Mockito.verify(screeningRepository).save(Mockito.any(Screening.class));
        Mockito.verifyNoMoreInteractions(screeningRepository, movieRepository, roomRepository, authenticator);
    }

    @Test
    public void testCreateScreeningShouldThrowExceptionWhenNonExistingMoviePassed() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);

        // When, then
        Assertions.assertThrows(NoSuchMovieException.class, () -> {
           underTest.createScreening(DUMMY_MOVIE_TITLE, DUMMY_ROOM_NAME, DUMMY_START_DATE);
        });
        Mockito.verify(authenticator).verify(allowedAccountLevels);

    }

    @Test
    public void testCreateScreeningShouldThrowExceptionWhenNonExistingRoomPassed() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);
        BDDMockito.given(movieRepository.findMovieByTitle(DUMMY_MOVIE_TITLE))
                .willReturn(Optional.of(new Movie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH)));

        // When, then
        Assertions.assertThrows(NoSuchRoomException.class, () -> {
            underTest.createScreening(DUMMY_MOVIE_TITLE, DUMMY_ROOM_NAME, DUMMY_START_DATE);
        });
        Mockito.verify(authenticator).verify(allowedAccountLevels);

    }

    @Test
    public void testCreateScreeningShouldNotCreateScreeningWhenPassedScreeningsEndOverlapsWithOtherScreeningsBeginning() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);
        Movie otherMovie = new Movie("Other", "Genre", 30);
        Room room = new Room(DUMMY_ROOM_NAME, DUMMY_ROOM_ROWS, DUMMY_ROOM_COLUMNS);
        Screening screeningToOverlapWith =
                new Screening(otherMovie, room, LocalDateTime.parse("2021-01-01 12:15", DATE_TIME_FORMATTER));
        BDDMockito.given(movieRepository.findMovieByTitle(DUMMY_MOVIE_TITLE))
                .willReturn(Optional.of(new Movie(DUMMY_MOVIE_TITLE, DUMMY_MOVIE_GENRE, DUMMY_MOVIE_LENGTH)));
        BDDMockito.given(roomRepository.findRoomByName(DUMMY_ROOM_NAME))
                .willReturn(Optional.of(room));
        BDDMockito.given(screeningRepository.findScreeningsByRoom(room)).willReturn(List.of(screeningToOverlapWith));
        String expected = "There is an overlapping screening";

        // When
        String actual = underTest.createScreening(DUMMY_MOVIE_TITLE, DUMMY_ROOM_NAME, DUMMY_START_DATE);

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(movieRepository).findMovieByTitle(DUMMY_MOVIE_TITLE);
        Mockito.verify(roomRepository).findRoomByName(DUMMY_ROOM_NAME);
        Mockito.verify(screeningRepository).findScreeningsByRoom(room);
        Mockito.verifyNoMoreInteractions(screeningRepository, movieRepository, roomRepository, authenticator);
        Assertions.assertEquals(actual, expected);
    }
}
