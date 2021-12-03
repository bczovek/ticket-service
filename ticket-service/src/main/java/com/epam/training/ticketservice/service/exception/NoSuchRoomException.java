package com.epam.training.ticketservice.service.exception;

public class NoSuchRoomException extends RuntimeException {

    public NoSuchRoomException(String message) {
        super(message);
    }
}
