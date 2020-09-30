package com.twitter.config;

import com.twitter.model.HttpUnauthorizedException;
import com.twitter.service.AuthenticationService;
import com.twitter.utils.SerializationUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Aspect
@Order(1)
public class AuthorizeAdvice {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SerializationUtils serializationUtils;

    @Around(value = "@annotation(target)", argNames = "joinPoint, target")
    public Object authorizeOperation (final ProceedingJoinPoint joinPoint,
                                       final Authorize target) throws IOException {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            System.out.println("Authorize");
            boolean isAuthorized = authenticationService.isUserAuthorized("username", "token");
            if (isAuthorized) {
                return joinPoint.proceed();
            }
            throw new HttpUnauthorizedException("Unauthorized", "You are not authorized");
        } catch (Throwable e) {
            return serializationUtils.serialize(new HttpUnauthorizedException("Unauthorized", "exception from AuthorizeImpl"));
        }
    }
}
