package com.epam.training.ticketservice.service.exception;

public class NoSuchMovieException extends RuntimeException {

    public NoSuchMovieException(String message) {
        super(message);
    }
}
