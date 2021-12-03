package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.RoomDTO;
import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.entity.Room;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final Authenticator authenticator;
    private final RoomRepository roomRepository;

    public RoomServiceImpl(final RoomRepository roomRepository, final Authenticator authenticator) {
        this.authenticator = authenticator;
        this.roomRepository = roomRepository;
    }

    @Override
    public void createRoom(String name, int numberOfRows, int numberOfColumns) {
        authenticator.verify(List.of(AccountLevel.ADMINISTRATOR));
        if (roomRepository.findRoomByName(name).isEmpty()) {
            Room room = new Room(name, numberOfRows, numberOfColumns);
            roomRepository.save(room);
        }
    }

    @Override
    public void updateRoom(String name, int numberOfRows, int numberOfColumns) {
        authenticator.verify(List.of(AccountLevel.ADMINISTRATOR));
        if (roomRepository.findRoomByName(name).isPresent()) {
            Room room = new Room(name, numberOfRows, numberOfColumns);
            roomRepository.save(room);
        }
    }

    @Override
    public List<RoomDTO> listRooms() {
        return roomRepository.findAll()
                .map(room -> new RoomDTO(room.getName(), room.getNumberOfRows(), room.getNumberOfColumns()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteRoom(String name) {
        authenticator.verify(List.of(AccountLevel.ADMINISTRATOR));
        if (roomRepository.findRoomByName(name).isPresent()) {
            roomRepository.deleteByName(name);
        }
    }
}
