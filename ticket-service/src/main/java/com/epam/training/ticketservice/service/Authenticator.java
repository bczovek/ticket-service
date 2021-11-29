package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.account.Account;
import com.epam.training.ticketservice.service.exception.IncorrectCredentialsException;

public interface Authenticator {

    void privilegedSignIn(String username, String password) throws IncorrectCredentialsException;

    void signIn(String username, String password);

    void signOut();

    boolean isAccountSignedIn();

    Account getSignedInAccount();
}
