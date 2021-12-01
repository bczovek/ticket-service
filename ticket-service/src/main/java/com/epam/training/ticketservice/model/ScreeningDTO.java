package com.epam.training.ticketservice.model;

import java.time.LocalDateTime;

public final class ScreeningDTO {

    private Long id;
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
}
