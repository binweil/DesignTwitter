package com.twitter.controller;

import com.twitter.model.HttpUnauthorizedException;
import com.twitter.service.AuthenticationService;
import com.twitter.utils.SerializationUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @CrossOrigin(allowCredentials = "true")
    @RequestMapping(value = "/JSON/login/{username}/{encryptPassword}", method = RequestMethod.GET)
    public @ResponseBody String getAuthenticationCookie (@PathVariable(value = "username") String username,
                                                         @PathVariable(value = "encryptPassword") String encryptPassword,
                                                         HttpServletResponse response) throws IOException {
        try {
            Map<String, String> res = new HashMap<>();
            String tokenFromDatabase = authenticationService.getToken(username, encryptPassword);
            Cookie cookie = new Cookie("authToken", tokenFromDatabase);
            cookie.setMaxAge(7*24*60*60);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            res.put("authToken", tokenFromDatabase);
            response.addCookie(cookie);
            return SerializationUtils.serialize(res);
        } catch (NotFoundException exception) {
            response.setStatus(404);
            return exception.toString();
        } catch (HttpUnauthorizedException exception) {
            response.setStatus(401);
            return SerializationUtils.serialize(exception);
        } catch (Exception exception) {
            exception.printStackTrace();
            response.setStatus(500);
            return exception.toString();
        }
    }
}
