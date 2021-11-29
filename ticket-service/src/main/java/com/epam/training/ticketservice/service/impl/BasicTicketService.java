package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.TicketService;

public class BasicTicketService implements TicketService {

    private final Authenticator authenticator;

    public BasicTicketService(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public Authenticator getAuthenticator() {
        return authenticator;
    }
}
