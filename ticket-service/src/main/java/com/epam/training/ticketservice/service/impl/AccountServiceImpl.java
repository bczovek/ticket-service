package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.account.Account;
import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.service.AccountService;
import com.epam.training.ticketservice.service.Authenticator;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final Authenticator authenticator;

    public AccountServiceImpl(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public void privilegedSignIn(String username, String password) {
        authenticator.verify(List.of(AccountLevel.UNAUTHORIZED));
        authenticator.privilegedSignIn(username, password);
    }

    @Override
    public void signIn(String username, String password) {

    }

    @Override
    public void signOut() {
        authenticator.verify(List.of(AccountLevel.ADMINISTRATOR));
        authenticator.signOut();
    }

    @Override
    public Account getAccount() {
        return authenticator.getAccount();
    }

    @Override
    public String describeAccount() {
        return authenticator.getAccount().describe();
    }
}
