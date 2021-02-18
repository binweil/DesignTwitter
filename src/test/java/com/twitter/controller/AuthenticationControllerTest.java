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

import static org.mockito.MockitoAnnotations.initMocks;


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
}
