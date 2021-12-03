package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.repository.entity.Movie;
import com.epam.training.ticketservice.repository.entity.Room;
import com.epam.training.ticketservice.repository.entity.Screening;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ScreeningRepository extends Repository<Screening, Long> {

    void save(Screening screening);

    Stream<Screening> findAll();

    void deleteByMovieAndRoomAndStartDateTime(Movie movie, Room room, LocalDateTime startDateTime);

    List<Screening> findScreeningsByRoom(Room room);
}
