package com.epam.training.ticketservice.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Screening {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Movie movie;
    @OneToOne
    private Room room;
    private LocalDateTime startDateTime;

    public Screening(Movie movie, Room room, LocalDateTime startDateTime) {
        this.movie = movie;
        this.room = room;
        this.startDateTime = startDateTime;
    }

    public Screening() {

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
