package com.epam.training.ticketservice.model;

import javax.persistence.Id;

public final class Movie {

    @Id
    private final String title;
    private final String genre;
    private final int length;

    public Movie(String title, String genre, int length) {
        this.title = title;
        this.genre = genre;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getLength() {
        return length;
    }

}
