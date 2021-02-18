package com.twitter.interceptor;

import com.auth0.jwk.JwkException;
import com.twitter.model.HttpUnauthorizedException;
import com.twitter.service.AuthenticationService;
import com.twitter.utils.SerializationUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import static com.twitter.utils.Constants.JWT_TOKEN;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwtToken = null;
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(JWT_TOKEN)) {
                        jwtToken = cookie.getValue();
                    }
                }
            }
            if (jwtToken == null) {
                throw new HttpUnauthorizedException("Unauthorized", "Cannot find JWT token in cookie");
            }
            boolean isAuthorized = authenticationService.isUserAuthorized(jwtToken);
            if (!isAuthorized) {
                response.setStatus(HttpStatus.SC_UNAUTHORIZED);
                throw new HttpUnauthorizedException("Unauthorized", "Invalid JWT token in cookie");
            }
            return true;
        } catch (HttpUnauthorizedException exception) {
            PrintWriter writer = response.getWriter();
            exception.printStackTrace();
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            writer.print(SerializationUtils.serialize(exception));
            return false;
        } catch (JwkException exception) {
            PrintWriter writer = response.getWriter();
            exception.printStackTrace();
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            writer.print(SerializationUtils.serialize(exception));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
