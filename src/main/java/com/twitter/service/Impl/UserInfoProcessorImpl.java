package com.twitter.service.Impl;

import com.twitter.dao.UserInfoDAO;
import com.twitter.model.UserInfoModel;
import com.twitter.service.UserInfoService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoProcessor")
public class UserInfoProcessorImpl implements UserInfoService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public UserInfoModel getUserInfo(String username) throws NotFoundException {
        return userInfoDAO.getUserInfo(username);
    }

    @Override
    public void addUserInfo(UserInfoModel user) throws Exception {
        userInfoDAO.addUserInfo(user);
    }

    @Override
    public void updateUserInfo(UserInfoModel user) throws NotFoundException {
        userInfoDAO.updateUserInfo(user);
    }
}
