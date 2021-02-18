package com.twitter.controller;

import com.twitter.model.UserInfoModel;
import com.twitter.service.UserInfoService;
import com.twitter.utils.SerializationUtils;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/JSON/get-user-info/{username}", method = RequestMethod.GET)
    public @ResponseBody String getUserInfo (@PathVariable(value = "username") String username,
                                            HttpServletResponse response) throws Exception {
        try {
            UserInfoModel userInfoModel = userInfoService.getUserInfo(username);
            return SerializationUtils.serialize(userInfoModel);
        } catch (NotFoundException exception) {
           response.setStatus(404);
           return SerializationUtils.serialize(exception);
        } catch (Exception exception) {
            response.setStatus(500);
            return SerializationUtils.serialize(exception);
        }

    }
}
