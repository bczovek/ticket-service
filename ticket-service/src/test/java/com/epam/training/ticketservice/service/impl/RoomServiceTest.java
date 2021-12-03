package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.entity.Movie;
import com.epam.training.ticketservice.repository.entity.Room;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class RoomServiceTest {

    private RoomService underTest;
    private static final String DUMMY_ROOM_NAME = "Name";
    private static final int DUMMY_ROOM_ROWS = 5;
    private static final int DUMMY_ROOM_COLUMNS = 5;

    @Mock
    private Authenticator authenticator;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new RoomServiceImpl(roomRepository, authenticator);
    }

    @Test
    public void testCreateRoomShouldSaveRoomWhenGivenNewRoom() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);

        // When
        underTest.createRoom(DUMMY_ROOM_NAME, DUMMY_ROOM_ROWS, DUMMY_ROOM_COLUMNS);

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(roomRepository).findRoomByName(DUMMY_ROOM_NAME);
        Mockito.verify(roomRepository).save(Mockito.any(Room.class));
        Mockito.verifyNoMoreInteractions(roomRepository);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testCreateRoomShouldNotSaveRoomWhenGivenExistingRoom() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);
        Room expected = new Room(DUMMY_ROOM_NAME, DUMMY_ROOM_ROWS, DUMMY_ROOM_COLUMNS);
        BDDMockito.given(roomRepository.findRoomByName(DUMMY_ROOM_NAME)).willReturn(Optional.of(expected));

        // When
        underTest.createRoom(DUMMY_ROOM_NAME, DUMMY_ROOM_ROWS, DUMMY_ROOM_COLUMNS);

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(roomRepository).findRoomByName(DUMMY_ROOM_NAME);
        Mockito.verifyNoMoreInteractions(roomRepository);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testUpdateRoomShouldUpdateRoomWhenGivenExistingRoom() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);
        Room expected = new Room(DUMMY_ROOM_NAME, DUMMY_ROOM_ROWS, DUMMY_ROOM_COLUMNS);
        BDDMockito.given(roomRepository.findRoomByName(DUMMY_ROOM_NAME)).willReturn(Optional.of(expected));

        // When
        underTest.updateRoom(DUMMY_ROOM_NAME, DUMMY_ROOM_ROWS, DUMMY_ROOM_COLUMNS);

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(roomRepository).findRoomByName(DUMMY_ROOM_NAME);
        Mockito.verify(roomRepository).save(Mockito.any(Room.class));
        Mockito.verifyNoMoreInteractions(roomRepository);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testUpdateRoomShouldNotUpdateRoomWhenGivenNewRoom() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);

        // When
        underTest.updateRoom(DUMMY_ROOM_NAME, DUMMY_ROOM_ROWS, DUMMY_ROOM_COLUMNS);

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(roomRepository).findRoomByName(DUMMY_ROOM_NAME);
        Mockito.verifyNoMoreInteractions(roomRepository);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testListRoomsShouldListRooms() {
        // Given in set up
        // When
        underTest.listRooms();

        // Then
        Mockito.verify(roomRepository).findAll();
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testDeleteRoomShouldDeleteRoomWhenGivenExistingRoom() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);
        Room expected = new Room(DUMMY_ROOM_NAME, DUMMY_ROOM_ROWS, DUMMY_ROOM_COLUMNS);
        BDDMockito.given(roomRepository.findRoomByName(DUMMY_ROOM_NAME)).willReturn(Optional.of(expected));

        // When
        underTest.deleteRoom(DUMMY_ROOM_NAME);

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(roomRepository).findRoomByName(DUMMY_ROOM_NAME);
        Mockito.verify(roomRepository).deleteByName(DUMMY_ROOM_NAME);
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testDeleteRoomShouldNotDeleteRoomWhenGivenNewRoom() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);

        // When
        underTest.deleteRoom(DUMMY_ROOM_NAME);

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(roomRepository).findRoomByName(DUMMY_ROOM_NAME);
        Mockito.verifyNoMoreInteractions(roomRepository);
    }
}
