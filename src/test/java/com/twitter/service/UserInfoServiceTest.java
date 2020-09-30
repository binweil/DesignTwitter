package com.twitter.service;

import com.twitter.dao.UserInfoDAO;
import com.twitter.model.UserInfoModel;
import com.twitter.service.Impl.UserInfoProcessorImpl;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserInfoServiceTest {

    @Mock
    private UserInfoDAO userInfoDAO;

    @InjectMocks
    private UserInfoService userInfoService = new UserInfoProcessorImpl();

    @Before
    public void initialize () {
        initMocks(this);
    }

    @Test
    public void testGetUserInfo () throws NotFoundException {
        UserInfoModel userInfoModel = new UserInfoModel();
        when(userInfoDAO.getUserInfo(any(String.class)))
                .thenReturn(userInfoModel);
        userInfoService.getUserInfo("test");
    }

    @Test(expected = NotFoundException.class)
    public void testGetUserInfoNotFoundException () throws NotFoundException {
        doThrow(NotFoundException.class).when(userInfoDAO).getUserInfo(any(String.class));
        userInfoService.getUserInfo("test");
    }

    @Test
    public void testAddUserInfo () throws Exception {
        UserInfoModel userInfoModel = new UserInfoModel();
        doNothing().when(userInfoDAO).addUserInfo(any(UserInfoModel.class));
        userInfoService.addUserInfo(userInfoModel);
    }

    @Test
    public void testUpdateUserInfo () throws Exception {
        UserInfoModel userInfoModel = new UserInfoModel();
        doNothing().when(userInfoDAO).updateUserInfo(any(UserInfoModel.class));
        userInfoService.updateUserInfo(userInfoModel);
    }
}
