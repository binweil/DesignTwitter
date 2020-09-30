package com.twitter.service;

import com.twitter.model.HttpUnauthorizedException;
import org.apache.ibatis.javassist.NotFoundException;

public interface AuthenticationService {

    boolean isUserAuthorized(String username, String token) throws Exception, HttpUnauthorizedException;

    String getToken(String username, String encryptPassword) throws Exception, HttpUnauthorizedException;
}
