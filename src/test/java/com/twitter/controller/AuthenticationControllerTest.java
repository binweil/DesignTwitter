package com.twitter.controller;


import com.twitter.model.HttpUnauthorizedException;
import com.twitter.service.AuthenticationService;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.Cookie;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @Before
    public void initialize () {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    public void testGetAuthenticationCookie () throws Exception {
        Cookie cookie = new Cookie("username", "test");
        when(authenticationService.getToken("test", "token"))
                .thenReturn("testToken");
        this.mockMvc.perform(get("/JSON/login/test/testToken")
                .cookie(cookie))
                .andExpect(status().is(200));
    }

    @Test
    public void testGetAuthenticationCookieNotFoundException () throws Exception {
        Cookie cookie = new Cookie("username", "test");
        doThrow(NotFoundException.class).when(authenticationService).getToken(any(String.class), any(String.class));
        this.mockMvc.perform(get("/JSON/login/test/testToken")
                .cookie(cookie))
                .andExpect(status().is(404));
    }

    @Test
    public void testGetAuthenticationCookieHttpUnauthorizedException () throws Exception {
        Cookie cookie = new Cookie("username", "test");
        doThrow(HttpUnauthorizedException.class).when(authenticationService).getToken(any(String.class), any(String.class));
        this.mockMvc.perform(get("/JSON/login/test/testToken")
                .cookie(cookie))
                .andExpect(status().is(401));
    }

    @Test
    public void testGetAuthenticationCookieException () throws Exception {
        Cookie cookie = new Cookie("username", "test");
        doThrow(Exception.class).when(authenticationService).getToken(any(String.class), any(String.class));
        this.mockMvc.perform(get("/JSON/login/test/testToken")
                .cookie(cookie))
                .andExpect(status().is(500));
    }
}
