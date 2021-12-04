package com.epam.training.ticketservice.util;

import com.epam.training.ticketservice.model.MovieDto;
import com.epam.training.ticketservice.model.RoomDto;
import com.epam.training.ticketservice.model.ScreeningDto;
import com.epam.training.ticketservice.repository.entity.Movie;
import com.epam.training.ticketservice.repository.entity.Room;
import com.epam.training.ticketservice.repository.entity.Screening;

public final class Utils {

    private Utils() {
    }

    public static ScreeningDto mapScreeningDto(Screening screening) {
        Movie movie = screening.getMovie();
        MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getGenre(), movie.getLength());

        Room room = screening.getRoom();
        RoomDto roomDto = new RoomDto(room.getName(), room.getNumberOfRows(), room.getNumberOfColumns());

        return new ScreeningDto(movieDto, roomDto, screening.getStartDateTime());
    }
}
