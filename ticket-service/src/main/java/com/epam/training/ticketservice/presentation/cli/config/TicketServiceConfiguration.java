package com.epam.training.ticketservice.presentation.cli.config;

import com.epam.training.ticketservice.repository.AdministratorCredentialsProvider;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.service.AccountService;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.impl.AccountServiceImpl;
import com.epam.training.ticketservice.service.impl.BasicAuthenticator;
import com.epam.training.ticketservice.service.impl.MovieServiceImpl;
import com.epam.training.ticketservice.service.impl.RoomServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketServiceConfiguration {

    private TicketServiceConfiguration(){

    }

    @Bean
    public Authenticator authenticator(AdministratorCredentialsProvider administratorCredentialsProvider){
        return new BasicAuthenticator(administratorCredentialsProvider);
    }

    @Bean
    public AccountService accountService(Authenticator authenticator){
        return new AccountServiceImpl(authenticator);
    }

    @Bean
    public MovieService movieService(MovieRepository movieRepository, Authenticator authenticator){
        return new MovieServiceImpl(movieRepository, authenticator);
    }

    @Bean
    public RoomService roomService(RoomRepository roomRepository, Authenticator authenticator){
        return new RoomServiceImpl(roomRepository, authenticator);
    }
}
