package com.epam.training.ticketservice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreeningDto {

    private final MovieDto movie;
    private final RoomDto room;
    private final LocalDateTime startDateTime;

    public ScreeningDto(MovieDto movie, RoomDto room, LocalDateTime startDateTime) {
        this.movie = movie;
        this.room = room;
        this.startDateTime = startDateTime;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public RoomDto getRoom() {
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
