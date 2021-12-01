package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.repository.entity.Room;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface RoomRepository extends Repository<Room, Long> {

    void save(Room room);

    Optional<Room> findRoomByName(String name);

    Stream<Room> findAll();

    void deleteByName(String name);
}
