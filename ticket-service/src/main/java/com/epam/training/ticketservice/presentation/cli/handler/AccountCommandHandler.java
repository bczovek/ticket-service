package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.account.Account;
import com.epam.training.ticketservice.service.TicketService;
import com.epam.training.ticketservice.service.exception.IncorrectCredentialsException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AccountCommandHandler {

    private final TicketService ticketService;

    public AccountCommandHandler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @ShellMethod(value = "Sign in as administrator", key = "sign in privileged")
    public String privilegedSignIn(String username, String password) {
        try {
            ticketService.getAuthenticator().privilegedSignIn(username, password);
            return "";
        } catch (IncorrectCredentialsException e) {
            return "Login failed due to incorrect credentials";
        }
    }

    @ShellMethod(value = "Sign out", key = "sign out")
    public String signOut() {
        ticketService.getAuthenticator().signOut();
        return "";
    }

    @ShellMethod(value = "Describe account", key = "describe account")
    public String describeAccount() {
        Account account = ticketService.getAuthenticator().getAccount();
        switch (account.getAccountLevel()) {
            case ADMINISTRATOR:
                return String.format("Signed in with privileged account %s", account.getUsername());
            case UNAUTHORIZED:
                return "You are not signed in";
            default:
                throw new UnknownError();
        }
    }
}
