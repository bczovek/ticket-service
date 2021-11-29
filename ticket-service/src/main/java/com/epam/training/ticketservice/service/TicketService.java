package com.epam.training.ticketservice.service;

import org.springframework.stereotype.Service;

@Service
public interface TicketService {

    Authenticator getAuthenticator();
}
