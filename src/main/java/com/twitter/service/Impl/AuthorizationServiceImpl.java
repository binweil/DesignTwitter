package com.twitter.service.Impl;

import com.twitter.dao.AuthenticationDAO;
import com.twitter.model.HttpUnauthorizedException;
import com.twitter.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service("authenticationService")
public class AuthorizationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationDAO authenticationDAO;

    @Override
    public boolean isUserAuthorized(String username, String token) throws Exception {
        String password = authenticationDAO.getPassword(username);
        if (authenticationDAO.isUserExist(username) && token.equals(SHA(username+password))) {
            return true;
        }
        throw new HttpUnauthorizedException("You are unauthorized to perform the operation", "");
    }

    @Override
    public String getToken(String username, String encryptPassword) throws Exception {
        String tokenFromDatabase = SHA(username + authenticationDAO.getPassword(username));
        if (tokenFromDatabase.equals(encryptPassword)) {
            return tokenFromDatabase;
        }
        throw new HttpUnauthorizedException("User " + username + "is not authorized", "");
    }

    public static String SHA (String str) throws Exception {
        if (str == null || str.isEmpty()) {
            return "";
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(str.getBytes());
        byte byteBuffer[] = messageDigest.digest();
        StringBuffer strHexString = new StringBuffer();
        for (int i = 0; i < byteBuffer.length; i++) {
            String hex = Integer.toHexString(0xff & byteBuffer[i]);
            if (hex.length() == 1) {
                strHexString.append('0');
            }
            strHexString.append(hex);
        }
        return strHexString.toString();
    }
}
