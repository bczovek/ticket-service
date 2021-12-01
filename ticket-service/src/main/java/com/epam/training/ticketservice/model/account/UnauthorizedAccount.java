package com.epam.training.ticketservice.model.account;

public final class UnauthorizedAccount implements Account{

    private final String username = "";
    private final AccountLevel accountLevel = AccountLevel.UNAUTHORIZED;


    UnauthorizedAccount() {
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public AccountLevel getAccountLevel() {
        return accountLevel;
    }

    @Override
    public String describe() {
        return "You are not signed in";
    }
}
