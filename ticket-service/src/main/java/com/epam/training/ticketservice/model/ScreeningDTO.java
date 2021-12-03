package com.epam.training.ticketservice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreeningDTO {

    private final MovieDTO movie;
    private final RoomDTO room;
    private final LocalDateTime startDateTime;

    public ScreeningDTO(MovieDTO movie, RoomDTO room, LocalDateTime startDateTime) {
        this.movie = movie;
        this.room = room;
        this.startDateTime = startDateTime;
    }

    public MovieDTO getMovie() {
        return movie;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    @Override
    public String toString() {
        return movie.getTitle() + " (" + movie.getGenre() + ", " + movie.getLength()
                + " minutes), screened in room " + room.getName() + ", at "
                + startDateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }
}
