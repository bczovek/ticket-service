package com.epam.training.ticketservice.presentation.cli.config;

import com.epam.training.ticketservice.repository.AdministratorCredentialsProvider;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.impl.BasicAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketServiceConfiguration {

    @Bean
    public Authenticator authenticator(AdministratorCredentialsProvider administratorCredentialsProvider){
        return new BasicAuthenticator(administratorCredentialsProvider);
    }



}
