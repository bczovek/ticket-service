package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.repository.AdministratorCredentialsProvider;
import com.epam.training.ticketservice.repository.entity.AdministratorCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class BasicAdministratorCredentialsProvider implements AdministratorCredentialsProvider {

    @Value("${administrator.username}")
    private String username;

    @Value("${administrator.password}")
    private String password;


    @Override
    public AdministratorCredentials getAdministratorCredentials() {
        return new AdministratorCredentials(username, password);
    }
}
