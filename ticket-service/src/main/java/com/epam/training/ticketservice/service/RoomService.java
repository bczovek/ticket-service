package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.RoomDto;

import java.util.List;

public interface RoomService {

    void createRoom(String name, int numberOfRows, int numberOfColumns);

    void updateRoom(String name, int numberOfRows, int numberOfColumns);

    List<RoomDto> listRooms();

    void deleteRoom(String name);

}
