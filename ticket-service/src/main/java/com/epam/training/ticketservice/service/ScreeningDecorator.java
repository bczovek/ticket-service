package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.ScreeningDTO;

import java.time.LocalDateTime;

public final class ScreeningDecorator {

    private final ScreeningDTO screening;

    public ScreeningDecorator(ScreeningDTO screening) {
        this.screening = screening;
    }

    public LocalDateTime getStartDateTime() {
        return screening.getStartDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return screening.getStartDateTime().plusMinutes(screening.getMovie().getLength());
    }

    public LocalDateTime getEndOfBreakAfterScreening() {
        return getEndDateTime().plusMinutes(10);
    }
}
