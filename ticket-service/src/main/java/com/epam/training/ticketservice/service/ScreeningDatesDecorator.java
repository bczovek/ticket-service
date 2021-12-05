package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.ScreeningDto;

import java.time.LocalDateTime;

public final class ScreeningDatesDecorator {

    private final ScreeningDto screening;

    public ScreeningDatesDecorator(ScreeningDto screening) {
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
