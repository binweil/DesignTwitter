package com.twitter.service;

import com.twitter.dao.AuthenticationDAO;
import com.twitter.model.HttpUnauthorizedException;
import com.twitter.service.Impl.AuthorizationServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.twitter.service.Impl.AuthorizationServiceImpl.SHA;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthorizationServiceTest {

    @Mock
    private AuthenticationDAO authenticationDAO;

    @InjectMocks
    private AuthenticationService authenticationService = new AuthorizationServiceImpl();

    @Before
    public void initialize() {
        initMocks(this);
    }

    @Test
    public void testIsUserAuthorized() throws Exception {
        String encryptPassword = SHA("test"+"password");
        when(this.authenticationDAO.getPassword("test"))
                .thenReturn("password");
        when(this.authenticationDAO.isUserExist("test"))
                .thenReturn(true);
        authenticationService.isUserAuthorized("test", encryptPassword);
    }

    @Test(expected = HttpUnauthorizedException.class)
    public void testIsUserAuthorizedHttpUnauthorizedException() throws Exception {
        String encryptPassword = SHA("test"+"password");
        when(this.authenticationDAO.getPassword("test"))
                .thenReturn("password");
        when(this.authenticationDAO.isUserExist("test"))
                .thenReturn(false);
        authenticationService.isUserAuthorized("test", encryptPassword);
    }

    @Test
    public void testGetToken() throws Exception {
        String encryptPassword = SHA("test"+"password");
        when(this.authenticationDAO.getPassword("test"))
                .thenReturn("password");
        authenticationService.getToken("test", encryptPassword);
    }

    @Test(expected = HttpUnauthorizedException.class)
    public void testGetTokenHttpUnauthorizedException() throws Exception {
        String encryptPassword = SHA("test"+"password");
        when(this.authenticationDAO.getPassword("test"))
                .thenReturn("passwordIncorrect");
        authenticationService.getToken("test", encryptPassword);
    }
}
