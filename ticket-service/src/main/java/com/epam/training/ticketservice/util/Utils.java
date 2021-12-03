package com.epam.training.ticketservice.util;

import com.epam.training.ticketservice.model.MovieDTO;
import com.epam.training.ticketservice.model.RoomDTO;
import com.epam.training.ticketservice.model.ScreeningDTO;
import com.epam.training.ticketservice.repository.entity.Movie;
import com.epam.training.ticketservice.repository.entity.Room;
import com.epam.training.ticketservice.repository.entity.Screening;

public final class Utils {

    private Utils() {
    }

    public static ScreeningDTO mapScreeningDTO(Screening screening) {
        Movie movie = screening.getMovie();
        MovieDTO movieDTO = new MovieDTO(movie.getTitle(), movie.getGenre(), movie.getLength());

        Room room = screening.getRoom();
        RoomDTO roomDTO = new RoomDTO(room.getName(), room.getNumberOfRows(), room.getNumberOfColumns());

        return new ScreeningDTO(movieDTO, roomDTO, screening.getStartDateTime());
    }
}
