package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.account.AccountFactory;
import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.service.AccountService;
import com.epam.training.ticketservice.service.Authenticator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

class AccountServiceTest {

    private AccountService underTest;

    public static final List<AccountLevel> ALLOWED_ACCOUNT_LEVELS = List.of(AccountLevel.UNAUTHORIZED);
    public static final String DUMMY_USERNAME = "username";

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
        String dummyPassword = "secret";

        // When
        underTest.privilegedSignIn(DUMMY_USERNAME, dummyPassword);

        // Then
        Mockito.verify(authenticator).verify(ALLOWED_ACCOUNT_LEVELS);
        Mockito.verify(authenticator).privilegedSignIn(DUMMY_USERNAME, dummyPassword);
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testSignOutShouldCallAuthenticator() {
        // Given in set up
        // When
        underTest.signOut();

        // Then
        Mockito.verify(authenticator).verify(List.of(AccountLevel.ADMINISTRATOR));
        Mockito.verify(authenticator).signOut();
        Mockito.verifyNoMoreInteractions(authenticator);
    }

    @Test
    public void testDescribeAccountShouldReturnCorrectStringWhenGivenUnauthorizedAccount() {
        // Given
        BDDMockito.given(authenticator.getAccount()).willReturn(AccountFactory.createUnauthorizedAccount());
        String expected = "You are not signed in";

        // When
        String actual = underTest.describeAccount();

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDescribeAccountShouldReturnCorrectStringWhenGivenAdministratorAccount() {
        // Given
        BDDMockito.given(authenticator.getAccount())
                .willReturn(AccountFactory.createAdministratorAccount(DUMMY_USERNAME));
        String expected = String.format("Signed in with privileged account '%s'", DUMMY_USERNAME);

        // When
        String actual = underTest.describeAccount();

        // Then
        Assertions.assertEquals(expected, actual);
    }
}
