package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.MovieDTO;
import com.epam.training.ticketservice.service.MovieService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.CollectionUtils;

import java.util.List;

@ShellComponent
public class MovieCommandHandler {

    private final MovieService movieService;

    public MovieCommandHandler(MovieService movieService) {
        this.movieService = movieService;
    }

    @ShellMethod(value = "Create a movie", key = "create movie")
    public void createMovie(String title, String genre, int length){
        movieService.createMovie(title, genre, length);
    }

    @ShellMethod(value = "Update a movie", key = "update movie")
    public void updateMovie(String title, String genre, int length){
        movieService.updateMovie(title, genre, length);
    }

    @ShellMethod(value = "Delete a movie", key = "delete movie")
    public void deleteMovie(String title){
        movieService.deleteMovie(title);
    }

    @ShellMethod(value = "List every movie", key = "list movies")
    public String listMovies(){
        List<MovieDTO> movieDTOs = movieService.listMovies();
        if(CollectionUtils.isEmpty(movieDTOs)){
            return "There are no movies at the moment";
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (MovieDTO movieDTO : movieDTOs) {
            stringBuilder.append(movieDTO.toString())
                    .append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);

        return stringBuilder.toString();
    }
}
