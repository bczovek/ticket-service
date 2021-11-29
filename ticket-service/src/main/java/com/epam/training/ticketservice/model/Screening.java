package com.epam.training.ticketservice.model;

import java.time.LocalDateTime;

public final class Screening {

    private Long id;
    private final Movie movie;
    private final Room room;
    private final LocalDateTime startDateTime;

    public Screening(Movie movie, Room room, LocalDateTime startDateTime) {
        this.movie = movie;
        this.room = room;
        this.startDateTime = startDateTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
}
