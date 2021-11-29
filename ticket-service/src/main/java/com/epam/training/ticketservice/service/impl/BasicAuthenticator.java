package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.account.Account;
import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.repository.AdministratorCredentialsProvider;
import com.epam.training.ticketservice.repository.entity.AdministratorCredentials;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.exception.IncorrectCredentialsException;
import com.epam.training.ticketservice.service.exception.SignInException;
import com.epam.training.ticketservice.service.exception.SignOutException;

public class BasicAuthenticator implements Authenticator {

    private Account signedInAccount = null;
    private AdministratorCredentialsProvider administratorCredentialsProvider;

    public BasicAuthenticator(AdministratorCredentialsProvider administratorCredentialsProvider) {
        this.administratorCredentialsProvider = administratorCredentialsProvider;
    }

    @Override
    public void privilegedSignIn(String username, String password) throws IncorrectCredentialsException {
        if (signedInAccount == null) {
            AdministratorCredentials administratorCredentials = administratorCredentialsProvider.getAdministratorCredentials();
            if (administratorCredentials.getUsername().equals(username)
                    && administratorCredentials.getPassword().equals(password)) {
                signedInAccount = new Account(username, AccountLevel.ADMINISTRATOR);
            } else {
                throw new IncorrectCredentialsException();
            }
        } else {
            throw new SignInException("User already signed in. Sign out first.");
        }
    }

    @Override
    public void signIn(String username, String password) {

    }

    @Override
    public void signOut() {
        if (signedInAccount != null) {
            signedInAccount = null;
        } else {
            throw new SignOutException("You are not signed in.");
        }
    }

    @Override
    public boolean isAccountSignedIn() {
        return signedInAccount != null;
    }

    @Override
    public Account getSignedInAccount() {
        return signedInAccount;
    }
}
