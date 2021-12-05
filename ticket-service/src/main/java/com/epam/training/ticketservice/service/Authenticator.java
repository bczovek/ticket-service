package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.account.Account;
import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.service.exception.IncorrectCredentialsException;
import com.epam.training.ticketservice.service.exception.OperationNotAllowedException;

import java.util.List;

public interface Authenticator {

    void privilegedSignIn(String username, String password);

    void signOut();

    Account getAccount();

    void verify(List<AccountLevel> listOfAllowedAccountLevels);
}
