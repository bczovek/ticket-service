package com.epam.training.ticketservice.model.account;

public class AccountFactory {

    private AccountFactory(){

    };

    public static Account createAdministratorAccount(String username){
        return new AdministratorAccount(username);
    }

    public static Account createUnauthorizedAccount(){
        return new UnauthorizedAccount();
    }
}
