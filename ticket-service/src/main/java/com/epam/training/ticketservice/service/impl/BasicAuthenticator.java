package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.account.Account;
import com.epam.training.ticketservice.model.account.AccountFactory;
import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.repository.AdministratorCredentialsProvider;
import com.epam.training.ticketservice.repository.entity.AdministratorCredentials;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.exception.IncorrectCredentialsException;
import com.epam.training.ticketservice.service.exception.OperationNotAllowedException;
import com.epam.training.ticketservice.service.exception.SignOutException;

import java.util.List;

public class BasicAuthenticator implements Authenticator {

    private final Account UNAUTHORIZED_ACCOUNT = AccountFactory.createUnauthorizedAccount();
    private Account account = UNAUTHORIZED_ACCOUNT;
    private final AdministratorCredentialsProvider administratorCredentialsProvider;

    public BasicAuthenticator(final AdministratorCredentialsProvider administratorCredentialsProvider) {
        this.administratorCredentialsProvider = administratorCredentialsProvider;
    }

    @Override
    public void privilegedSignIn(String username, String password) throws IncorrectCredentialsException {
        AdministratorCredentials administratorCredentials = administratorCredentialsProvider.getAdministratorCredentials();
        if (administratorCredentials.getUsername().equals(username)
                && administratorCredentials.getPassword().equals(password)) {
            account = AccountFactory.createAdministratorAccount(username);
        } else {
            throw new IncorrectCredentialsException();
        }
    }

    @Override
    public void signIn(String username, String password) {

    }

    @Override
    public void signOut() {
        account = UNAUTHORIZED_ACCOUNT;
    }

    @Override
    public Account getAccount() {
        return account;
    }

    @Override
    public void verify(List<AccountLevel> listOfAllowedAccountLevels) throws OperationNotAllowedException {
        if(!listOfAllowedAccountLevels.contains(account.getAccountLevel())){
            throw new OperationNotAllowedException("Operation not allowed for your account!");
        }
    }
}
