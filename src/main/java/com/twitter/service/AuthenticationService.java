package com.twitter.service;

import com.auth0.jwk.JwkException;
import com.twitter.model.HttpUnauthorizedException;

public interface AuthenticationService {

    boolean isUserAuthorized(String token) throws HttpUnauthorizedException, JwkException;
}
