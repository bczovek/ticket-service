package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.MovieDTO;
import com.epam.training.ticketservice.model.RoomDTO;
import com.epam.training.ticketservice.model.ScreeningDTO;
import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import com.epam.training.ticketservice.repository.entity.Movie;
import com.epam.training.ticketservice.repository.entity.Room;
import com.epam.training.ticketservice.repository.entity.Screening;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.ScreeningDecorator;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.util.Utils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ScreeningServiceImpl implements ScreeningService {

    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final Authenticator authenticator;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository, MovieRepository movieRepository,
                                RoomRepository roomRepository, Authenticator authenticator) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.authenticator = authenticator;
    }

    @Override
    public String createScreening(String movieTitle, String roomName, String dateTime) {
        authenticator.verify(List.of(AccountLevel.ADMINISTRATOR));
        Movie movie = movieRepository.findMovieByTitle(movieTitle).orElseThrow();
        Room room = roomRepository.findRoomByName(roomName).orElseThrow();
        LocalDateTime startDateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);

        List<ScreeningDecorator> screeningsInRoom = screeningRepository.findScreeningsByRoom(room).stream()
                .map(Utils::mapScreeningDTO)
                .map(ScreeningDecorator::new)
                .collect(Collectors.toList());

        for (ScreeningDecorator screening : screeningsInRoom) {
            if (startDateTime.isAfter(screening.getStartDateTime())
                    && startDateTime.plusMinutes(movie.getLength()).isBefore(screening.getEndDateTime())) {
                return "There is an overlapping screening";
            }
            if (startDateTime.isBefore(screening.getStartDateTime())
                    && startDateTime.plusMinutes(movie.getLength()).isBefore(screening.getEndDateTime())
                    && startDateTime.plusMinutes(movie.getLength()).isAfter(screening.getStartDateTime())) {
                return "There is an overlapping screening";
            }
            if (startDateTime.isBefore(screening.getEndDateTime())
                    && startDateTime.plusMinutes(movie.getLength()).isAfter(screening.getEndDateTime())) {
                return "There is an overlapping screening";
            }
            if (startDateTime.isAfter(screening.getEndDateTime())
                    && startDateTime.isBefore(screening.getEndOfBreakAfterScreening())) {
                return "This would start in the break period after another screening in this room";
            }
        }

        screeningRepository.save(new Screening(movie, room, startDateTime));
        return "Screening created";
    }

    @Override
    @Transactional
    public void deleteScreening(String movieTitle, String roomName, String dateTime) {
        authenticator.verify(List.of(AccountLevel.ADMINISTRATOR));
        Room room = roomRepository.findRoomByName(roomName).orElseThrow();
        LocalDateTime startDateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);

        screeningRepository.deleteByRoomAndStartDateTime(room, startDateTime);
    }

    @Override
    public List<ScreeningDTO> listScreenings() {
        return screeningRepository.findAll().map(Utils::mapScreeningDTO)
                .collect(Collectors.toList());
    }

}
