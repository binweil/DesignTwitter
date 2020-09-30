package com.twitter.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDAO {

    @Select("SELECT password FROM UserInfoTable WHERE (username=#{username})")
    public String getPassword(@Param(value = "username") String username) throws NotFoundException;

    @Select("SELECT EXISTS (SELECT * FROM UserInfoTable WHERE (username=#{username}))")
    public boolean isUserExist (@Param(value = "username") String username) throws NotFoundException;

}
