package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.service.exception.IncorrectCredentialsException;
import com.epam.training.ticketservice.service.exception.OperationNotAllowedException;

public interface AccountService {

    String describeAccount();

    void privilegedSignIn(String username, String password) throws IncorrectCredentialsException, OperationNotAllowedException;

    void signIn(String username, String password);

    void signOut() throws OperationNotAllowedException;
}
