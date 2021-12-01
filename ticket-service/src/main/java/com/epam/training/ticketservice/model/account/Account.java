package com.epam.training.ticketservice.model.account;

public interface Account {

    String getUsername();

    AccountLevel getAccountLevel();

    String describe();
}
