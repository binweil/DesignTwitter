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

import javax.servlet.http.Cookie;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserInfoControllerTest {

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
        Cookie cookie = new Cookie("authToken", "authToken");
        when(userInfoService.getUserInfo("test"))
                .thenReturn(userInfoModel);
        when(authenticationService.isUserAuthorized("test", "authToken"))
                .thenReturn(true);
        this.mockMvc.perform(get("/JSON/get-user-info/test")
                .cookie(cookie))
                .andExpect(status().is(200));
    }

    @Test
    public void testGetUserInfoNotFoundException() throws Exception {
        UserInfoModel userInfoModel = new UserInfoModel();
        Cookie cookie = new Cookie("authToken", "authToken");
        doThrow(NotFoundException.class).when(userInfoService).getUserInfo("test");
        when(authenticationService.isUserAuthorized("test", "authToken"))
                .thenReturn(true);
        this.mockMvc.perform(get("/JSON/get-user-info/test")
                .cookie(cookie))
                .andExpect(status().is(404));
    }

    @Test
    public void testGetUserInfoHttpUnauthorizedException() throws Exception {
        UserInfoModel userInfoModel = new UserInfoModel();
        Cookie cookie = new Cookie("authToken", "authToken");
        when(userInfoService.getUserInfo("test"))
                .thenReturn(userInfoModel);
        doThrow(HttpUnauthorizedException.class)
                .when(authenticationService).isUserAuthorized("test", "authToken");
        this.mockMvc.perform(get("/JSON/get-user-info/test")
                .cookie(cookie))
                .andExpect(status().is(401));
    }

    @Test
    public void testGetUserInfoException() throws Exception {
        UserInfoModel userInfoModel = new UserInfoModel();
        Cookie cookie = new Cookie("authToken", "authToken");
        when(userInfoService.getUserInfo("test"))
                .thenReturn(userInfoModel);
        doThrow(Exception.class)
                .when(authenticationService).isUserAuthorized("test", "authToken");
        this.mockMvc.perform(get("/JSON/get-user-info/test")
                .cookie(cookie))
                .andExpect(status().is(500));
    }
}
