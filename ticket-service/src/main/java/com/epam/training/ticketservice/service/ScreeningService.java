package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.ScreeningDTO;

import java.util.List;

public interface ScreeningService {

    String createScreening(String movieTitle, String roomName, String dateTime);

    void deleteScreening(String movieTitle, String roomName, String dateTime);

    List<ScreeningDTO> listScreenings();
}
