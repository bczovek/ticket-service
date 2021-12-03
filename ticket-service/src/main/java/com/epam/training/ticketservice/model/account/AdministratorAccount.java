package com.epam.training.ticketservice.model.account;

public final class AdministratorAccount implements Account {

    private final String username;
    private final AccountLevel accountLevel = AccountLevel.ADMINISTRATOR;

    AdministratorAccount(final String username) {
        this.username = username;
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
        return String.format("Signed in with privileged account '%s'", this.getUsername());
    }
}
