package com.twitter.service;

import com.twitter.model.UserInfoModel;
import org.apache.ibatis.javassist.NotFoundException;

public interface UserInfoService {

    public UserInfoModel getUserInfo(String username) throws NotFoundException;

    public void addUserInfo(UserInfoModel user) throws Exception;

    public void updateUserInfo(UserInfoModel user) throws NotFoundException;
}
