package com.epam.training.ticketservice.model.account;

public final class Account {

    private final String username;
    private final AccountLevel accountLevel;

    public Account(String username, AccountLevel accountLevel) {
        this.username = username;
        this.accountLevel = accountLevel;
    }

    public String getUsername() {
        return username;
    }

    public AccountLevel getAccountLevel() {
        return accountLevel;
    }
}
