package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.model.Room;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RoomRepository extends Repository<Room, Long> {
    void save();

    Room findRoomByName();

    List<Room> findALl();

    void deleteByName();
}
