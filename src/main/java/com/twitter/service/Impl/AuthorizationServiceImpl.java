package com.twitter.service.Impl;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.twitter.model.HttpUnauthorizedException;
import com.twitter.service.AuthenticationService;
import com.twitter.utils.AppConfig;

import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;

@Service("authenticationService")
public class AuthorizationServiceImpl implements AuthenticationService {

    private JwkProvider provider;

    public AuthorizationServiceImpl () throws MalformedURLException {
        String region = (String) AppConfig.getParameter("Beta", "region");;
        String userPoolID = (String) AppConfig.getParameter("Beta", "userPoolID");
        String keysURL = String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", region, userPoolID);
        this.provider = new UrlJwkProvider(URI.create(keysURL).toURL());
    }

    @Override
    public boolean isUserAuthorized(String token) throws HttpUnauthorizedException, JwkException {
        DecodedJWT jwt = JWT.decode(token);
        Jwk jwk = this.provider.get(jwt.getKeyId());
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
        algorithm.verify(jwt);
        // Check Expire Token
        if (jwt.getExpiresAt().before(Calendar.getInstance().getTime())) {
            throw new HttpUnauthorizedException("Unauthorized", "Expired Token!");
        }
        return true;
    }
}
