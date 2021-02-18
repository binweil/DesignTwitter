package com.twitter.controller;

import com.twitter.model.HttpUnauthorizedException;
import com.twitter.model.UserInfoModel;
import com.twitter.service.AuthenticationService;
import com.twitter.service.UserInfoService;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserInfoControllerTest {

    private static final String token = "eyJraWQiOiI0RmhTbWpcL1llV0FaT3V4OHNFV1wvOGY1Qms2VWJWOXZkeERlbWdIWk1iaTA9IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiI1NDA5N2I2YS1kZGNlLTRmMTAtODhiMS0zZDEwNGU4YjY5YmUiLCJldmVudF9pZCI6ImY0ODFjMDU3LTVlNjktNDM4Zi1iMTA5LTE3ZDBmYzlkMjM2MiIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE2MTM2MjM5NDksImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy13ZXN0LTIuYW1hem9uYXdzLmNvbVwvdXMtd2VzdC0yXzN2elA1NUVZbiIsImV4cCI6MTYxMzYyNzU0OSwiaWF0IjoxNjEzNjIzOTQ5LCJqdGkiOiJmNDI1NDljNS1hNzQwLTQ1YmYtOGZlMS04MjdjZWJjYzA2ODEiLCJjbGllbnRfaWQiOiI1NWF0ZnR2cHZmZGNybmd1MjJpNWI3MjdwZiIsInVzZXJuYW1lIjoibGFteSJ9.mEHqvbdKtHIPOadKgKo0rpltBBENFmFef4YMSvMrwtKA4Z23F5mMvq2-naBvRf1HFJeeeMPYxt7I910Zq9XEozbubhuUmqmz5d0l0Qvra-ex3mPSbRoKfXOYAcr7mLBh1_h2LfuVzLOfRbjBClli-fEGhQcuYsTh0CPESqugavQ6GrhCHgoeE7ptfc5ZEOI7UxqEkk_R1kAY1C4xCpasesx9hGvXhtj4hCnJ8txTqyGLEcAeHEdaqn6M_I00tQn8jney58AoQvoBaWoWmyTtbNOwHwOe3MTANgvtRCbzVa8Jn6sqr1j4XHMIw4MpPnA0NYEIB9LMw4KfrjOvUqNTRw";

    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private UserInfoService userInfoService;

    @InjectMocks
    private UserInfoController userInfoController;

    @Before
    public void initialize() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userInfoController).build();
    }

    @Test
    public void testGetUserInfo() throws Exception {
        UserInfoModel userInfoModel = new UserInfoModel();
        when(userInfoService.getUserInfo("test"))
                .thenReturn(userInfoModel);
        this.mockMvc.perform(get("/JSON/get-user-info/test"))
                .andExpect(status().is(200));
    }

    @Test
    public void testGetUserInfoNotFoundException() throws Exception {
        UserInfoModel userInfoModel = new UserInfoModel();
        doThrow(NotFoundException.class).when(userInfoService).getUserInfo("test");
        this.mockMvc.perform(get("/JSON/get-user-info/test"))
                .andExpect(status().is(404));
    }

    @Test
    public void testGetUserInfoHttpUnauthorizedException() throws Exception {
        UserInfoModel userInfoModel = new UserInfoModel();
        when(userInfoService.getUserInfo("test"))
                .thenReturn(userInfoModel);
        this.mockMvc.perform(get("/JSON/get-user-info/test"))
                .andExpect(status().is(401));
    }

    @Test
    public void testGetUserInfoException() throws Exception {
        UserInfoModel userInfoModel = new UserInfoModel();
        when(userInfoService.getUserInfo("test"))
                .thenReturn(userInfoModel);
        doThrow(Exception.class)
                .when(authenticationService).isUserAuthorized( "authToken");
        this.mockMvc.perform(get("/JSON/get-user-info/test"))
                .andExpect(status().is(500));
    }
}
