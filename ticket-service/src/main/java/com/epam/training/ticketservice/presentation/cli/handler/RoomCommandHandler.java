package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.RoomDto;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.CollectionUtils;

import java.util.List;

@ShellComponent
public class RoomCommandHandler {

    private final RoomService roomService;

    public RoomCommandHandler(RoomService roomService) {
        this.roomService = roomService;
    }

    @ShellMethod(value = "Create a room", key = "create room")
    public void createRoom(String name, int numberOfRows, int numberOfColumns) {
        roomService.createRoom(name, numberOfRows, numberOfColumns);
    }

    @ShellMethod(value = "Update a room", key = "update room")
    public void updateRoom(String name, int numberOfRows, int numberOfColumns) {
        roomService.updateRoom(name, numberOfRows, numberOfColumns);
    }

    @ShellMethod(value = "List all rooms", key = "list rooms")
    public String listRooms() {
        List<RoomDto> roomDtos = roomService.listRooms();
        if (CollectionUtils.isEmpty(roomDtos)) {
            return "There are no rooms at the moment";
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (RoomDto roomDto : roomDtos) {
            stringBuilder.append(roomDto.toString())
                    .append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    @ShellMethod(value = "Delete a room", key = "delete room")
    public void deleteRoom(String name) {
        roomService.deleteRoom(name);
    }
}
