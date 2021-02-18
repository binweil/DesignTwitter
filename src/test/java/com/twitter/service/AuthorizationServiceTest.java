package com.twitter.service;

import com.twitter.dao.AuthenticationDAO;
import com.twitter.model.HttpUnauthorizedException;
import com.twitter.service.Impl.AuthorizationServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.MalformedURLException;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthorizationServiceTest {

    private static final String token = "eyJraWQiOiI0RmhTbWpcL1llV0FaT3V4OHNFV1wvOGY1Qms2VWJWOXZkeERlbWdIWk1iaTA9IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiI1NDA5N2I2YS1kZGNlLTRmMTAtODhiMS0zZDEwNGU4YjY5YmUiLCJldmVudF9pZCI6ImY0ODFjMDU3LTVlNjktNDM4Zi1iMTA5LTE3ZDBmYzlkMjM2MiIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE2MTM2MjM5NDksImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy13ZXN0LTIuYW1hem9uYXdzLmNvbVwvdXMtd2VzdC0yXzN2elA1NUVZbiIsImV4cCI6MTYxMzYyNzU0OSwiaWF0IjoxNjEzNjIzOTQ5LCJqdGkiOiJmNDI1NDljNS1hNzQwLTQ1YmYtOGZlMS04MjdjZWJjYzA2ODEiLCJjbGllbnRfaWQiOiI1NWF0ZnR2cHZmZGNybmd1MjJpNWI3MjdwZiIsInVzZXJuYW1lIjoibGFteSJ9.mEHqvbdKtHIPOadKgKo0rpltBBENFmFef4YMSvMrwtKA4Z23F5mMvq2-naBvRf1HFJeeeMPYxt7I910Zq9XEozbubhuUmqmz5d0l0Qvra-ex3mPSbRoKfXOYAcr7mLBh1_h2LfuVzLOfRbjBClli-fEGhQcuYsTh0CPESqugavQ6GrhCHgoeE7ptfc5ZEOI7UxqEkk_R1kAY1C4xCpasesx9hGvXhtj4hCnJ8txTqyGLEcAeHEdaqn6M_I00tQn8jney58AoQvoBaWoWmyTtbNOwHwOe3MTANgvtRCbzVa8Jn6sqr1j4XHMIw4MpPnA0NYEIB9LMw4KfrjOvUqNTRw";

    @Mock
    private AuthenticationDAO authenticationDAO;

    @InjectMocks
    private AuthorizationServiceImpl authorizationService;

    @Before
    public void initialize() throws MalformedURLException {
        initMocks(this);
        authorizationService = new AuthorizationServiceImpl();
    }

    @Test
    public void testIsUserAuthorized() throws Exception {
        authorizationService.isUserAuthorized(token);
    }
}
