package com.epam.training.ticketservice.service;

public interface Authenticator {

    void signIn(String username, String password);

    void signOut();

}
