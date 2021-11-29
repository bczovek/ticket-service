package com.epam.training.ticketservice.repository.entity;

public final class AdministratorCredentials {

    private final String username;
    private final String password;

    public AdministratorCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
