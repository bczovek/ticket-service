package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.account.Account;
import com.epam.training.ticketservice.service.AccountService;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.exception.IncorrectCredentialsException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AccountCommandHandler {

    private final AccountService accountService;

    public AccountCommandHandler(final AccountService accountService) {
        this.accountService = accountService;
    }

    @ShellMethod(value = "Sign in as administrator", key = "sign in privileged")
    public String privilegedSignIn(String username, String password) {
        try {
            accountService.privilegedSignIn(username, password);
            return "";
        } catch (IncorrectCredentialsException e) {
            return "Login failed due to incorrect credentials";
        }
    }

    @ShellMethod(value = "Sign out", key = "sign out")
    public String signOut() {
        accountService.signOut();
        return "";
    }

    @ShellMethod(value = "Describe account", key = "describe account")
    public String describeAccount() {
        return accountService.describeAccount();
    }
}
