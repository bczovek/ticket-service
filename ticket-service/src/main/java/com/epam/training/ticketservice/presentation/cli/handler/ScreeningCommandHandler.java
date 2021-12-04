package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.ScreeningDto;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.CollectionUtils;

import java.util.List;

@ShellComponent
public class ScreeningCommandHandler {

    private final ScreeningService screeningService;

    public ScreeningCommandHandler(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @ShellMethod(value = "Create a screening", key = "create screening")
    public String createScreening(String movieTitle, String roomName, String startDateTime) {
        return screeningService.createScreening(movieTitle, roomName, startDateTime);
    }

    @ShellMethod(value = "Update a screening", key = "delete screening")
    public void deleteScreening(String movieTitle, String roomName, String startDateTime) {
        screeningService.deleteScreening(movieTitle, roomName, startDateTime);
    }

    @ShellMethod(value = "List all screenings", key = "list screenings")
    public String listScreenings() {
        List<ScreeningDto> screeningDtos = screeningService.listScreenings();
        if (CollectionUtils.isEmpty(screeningDtos)) {
            return "There are no screenings";
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (ScreeningDto screeningDto : screeningDtos) {
            stringBuilder.append(screeningDto.toString())
                    .append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }
}
