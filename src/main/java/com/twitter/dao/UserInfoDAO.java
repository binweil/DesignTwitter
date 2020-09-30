package com.twitter.dao;

import com.twitter.model.UserInfoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDAO {

    @Select("SELECT * FROM UserInfoTable WHERE (username=#{username})")
    public UserInfoModel getUserInfo(@Param("username") String username) throws NotFoundException;

    @Select("INSERT * FROM UserInfoTable (username, password, name, age, region) VALUES" +
            "(#{user.username}, #{user.password}, #{user.name}, #{user.age}, #{user.region})")
    public void addUserInfo(@Param("user") UserInfoModel user) throws Exception;

    @Select("UPDATE UserInfoTable SET name=#{user.name}, age=#{user.age}, region=#{user.region}) WHERE " +
            "(username=#{user.username})")
    public void updateUserInfo(@Param("user") UserInfoModel user) throws NotFoundException;

    @Select("UPDATE UserInfoTable SET password=#{user.password}) WHERE " +
            "(username=#{user.username})")
    public void updateUserPassword(@Param("user") UserInfoModel user) throws NotFoundException;
}
