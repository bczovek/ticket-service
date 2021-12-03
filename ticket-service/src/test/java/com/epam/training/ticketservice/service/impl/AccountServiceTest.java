package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.service.AccountService;
import com.epam.training.ticketservice.service.Authenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

class AccountServiceTest {

    private AccountService underTest;

    @Mock
    private Authenticator authenticator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new AccountServiceImpl(authenticator);
    }

    @Test
    public void testPrivilegedSignInShouldCallAuthenticator(){
        // Given
        String dummyUsername = "username";
        String dummyPassword = "secret";
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.UNAUTHORIZED);

        // When
        underTest.privilegedSignIn(dummyUsername, dummyPassword);

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(authenticator).privilegedSignIn(dummyUsername, dummyPassword);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testPrivilegedSignOutShouldCallAuthenticator() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);

        // When
        underTest.signOut();

        // Then
        Mockito.verify(authenticator).verify(allowedAccountLevels);
        Mockito.verify(authenticator).signOut();
        Mockito.verifyNoMoreInteractions(authenticator);
    }
}
