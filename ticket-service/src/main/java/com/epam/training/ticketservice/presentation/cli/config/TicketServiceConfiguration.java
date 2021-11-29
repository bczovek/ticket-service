package com.epam.training.ticketservice.presentation.cli.config;

import com.epam.training.ticketservice.repository.AdministratorCredentialsProvider;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.TicketService;
import com.epam.training.ticketservice.service.impl.BasicAuthenticator;
import com.epam.training.ticketservice.service.impl.BasicTicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketServiceConfiguration {

    @Bean
    public Authenticator authenticator(AdministratorCredentialsProvider administratorCredentialsProvider){
        return new BasicAuthenticator(administratorCredentialsProvider);
    }

    @Bean
    public TicketService ticketService(Authenticator authenticator, MovieRepository movieRepository){
        return new BasicTicketService(authenticator, movieRepository);
    }

}
