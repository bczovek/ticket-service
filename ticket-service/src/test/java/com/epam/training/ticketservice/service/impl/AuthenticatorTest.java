package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.account.AccountLevel;
import com.epam.training.ticketservice.repository.AdministratorCredentialsProvider;
import com.epam.training.ticketservice.repository.entity.AdministratorCredentials;
import com.epam.training.ticketservice.service.Authenticator;
import com.epam.training.ticketservice.service.exception.IncorrectCredentialsException;
import com.epam.training.ticketservice.service.exception.OperationNotAllowedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class AuthenticatorTest {

    private Authenticator underTest;
    private static final String DUMMY_USERNAME = "test";
    private static final String DUMMY_PASSWORD = "secret";

    @Mock
    private AdministratorCredentialsProvider administratorCredentialsProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new BasicAuthenticator(administratorCredentialsProvider);
        BDDMockito.given(administratorCredentialsProvider.getAdministratorCredentials())
                .willReturn(new AdministratorCredentials(DUMMY_USERNAME, DUMMY_PASSWORD));
    }

    @Test
    public void testPrivilegedSignInShouldSignInWhenPassedCorrectCredentials() {
        // Given in set up

        // When
        underTest.privilegedSignIn(DUMMY_USERNAME, DUMMY_PASSWORD);

        // Then
        Assertions.assertEquals(underTest.getAccount().getAccountLevel(), AccountLevel.ADMINISTRATOR);
    }

    @Test
    public void testPrivilegedSignInShouldThrowExceptionWhenGivenIncorrectCredentials() {
        // Given in set up

        // When, then
        Assertions.assertThrows(IncorrectCredentialsException.class, () -> {
            underTest.privilegedSignIn(DUMMY_USERNAME, "Secret");
        });
    }

    @Test
    public void testSignOutShouldSignOut() {
        // Given
        underTest.privilegedSignIn(DUMMY_USERNAME, DUMMY_PASSWORD);

        // When
        underTest.signOut();

        // Then
        Assertions.assertEquals(underTest.getAccount().getAccountLevel(), AccountLevel.UNAUTHORIZED);
    }

    @Test
    public void testVerifyShouldThrowExceptionWhenGivenAccountIsNotAllowed() {
        // Given
        List<AccountLevel> allowedAccountLevels = List.of(AccountLevel.ADMINISTRATOR);

        // When, then
        Assertions.assertThrows(OperationNotAllowedException.class, () -> {
            underTest.verify(allowedAccountLevels);
        });
    }
}
